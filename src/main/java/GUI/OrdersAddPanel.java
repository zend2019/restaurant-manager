package main.java.GUI;

import main.java.BL.Contract.Logic.*;
import main.java.BL.Contract.Order;
import main.java.BL.Contract.OrderStatus;
import main.java.BL.Contract.Product;
import main.java.GUI.commonUI.Common;
import main.java.common.DateUtils;
import main.java.common.StringUtils;
import main.java.common.constants.Constants;
import main.java.common.constants.DatabaseConstants;
import main.java.common.constants.GUIConstants;
import main.java.database.DatabaseController;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Date;
import java.util.HashMap;
import java.util.Vector;

import static java.lang.Integer.valueOf;
import static main.java.database.DatabaseController.getAllCategoryNames;
import static main.java.database.DatabaseController.getAllProviderCompanyName;

public class OrdersAddPanel extends IWorkPanel {
    private JLabel providerLabel;
    private JLabel categoryLabel;
    private JLabel itemNameLabel;
    private JLabel unitsLabel;
    private JLabel orderSumLabel;
    private JLabel orderSumFieldLabel;
    private JLabel oneRequired;
    private JLabel noResults;
    private JLabel searchCompleted;
    private JLabel placeOrderErrorLabel;
    private JComboBox providersList;
    private JComboBox categoryList;
    private JTextField unitsTF;
    private JTextField itemNameTF;
    private JButton searchItemButton;
    private JButton placeOrderButton;
    private JTable itemsTable;
    private JTable orderTable;
    private DefaultTableModel ordersTableModel;
    private DefaultTableModel itemsTableModel;
    private JScrollPane scrollItemsTable;
    private JScrollPane scrollOrderTable;
    private JPanel searchPanel;
    private JPanel tablesPanel;
    private JPanel itemsTablePanel;
    private JPanel orderTablePanel;
    private JPanel placeOrderPanel;
    private EditItemDialog itemDialog;
    private OrderPlacedDialog orderPlacedDialog;
    private Integer orderSum = 0;
    private Vector<String> providers;
    private Vector<String> categories;
    private String orderItemId;
    private int orderItemAmount;
    private IProductManager productManager;
    private IOrderManager orderManager;
    private IProviderManaging providerManaging;

    //Tables column FIELDS//
    private String[] itemsColumnNames = {"ID", "Item name", "Category", "Provider", "Available units", "Price per Item", "Expiration date"};
    private String[] orderColumnNames = {"ID", "Item name", "Category", "Provider", "Selected units", "Price per Item", "Expiration date"};


    public OrdersAddPanel() {
        initialization();
        setSearchPanelLayout();
        setTableLayout();
        setPlaceOrderLayout();
        setMainLayout();
        setActionListeners();
        productManager = new ProductController();
        orderManager = new OrderController();
        providerManaging = new ProviderController();
    }

    @Override
    protected void initialization() {
        providerLabel = new JLabel(GUIConstants.PROVIDER);
        categoryLabel = new JLabel(GUIConstants.CATEGORY);
        itemNameLabel = new JLabel(GUIConstants.ITEM_NAME);
        unitsLabel = new JLabel(GUIConstants.CURRENT_AMOUNT);
        orderSumLabel = new JLabel(GUIConstants.ORDER_SUM);
        orderSumFieldLabel = new JLabel(GUIConstants.ZERO);
        oneRequired = new JLabel(GUIConstants.ATLEAST_ONE_FIELD_REQUIRED);
        noResults = new JLabel(GUIConstants.NO_RESULTS);
        searchCompleted = new JLabel(GUIConstants.SEARCH_COMPLETED);
        placeOrderErrorLabel = new JLabel(GUIConstants.PLACE_ORDER_ERROR);
        providersList = new JComboBox();
        categoryList = new JComboBox();
        unitsTF = new JTextField(10);
        itemNameTF = new JTextField(10);
        searchItemButton = new JButton(GUIConstants.SEARCH);
        placeOrderButton = new JButton(GUIConstants.PLACE_ORDER);
        ordersTableModel = new DefaultTableModel(null, orderColumnNames);
        itemsTableModel = new DefaultTableModel(null, itemsColumnNames);
        itemsTable = new JTable(itemsTableModel) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        orderTable = new JTable(ordersTableModel) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        scrollItemsTable = new JScrollPane(itemsTable);
        scrollOrderTable = new JScrollPane(orderTable);
        searchPanel = new JPanel();
        itemsTablePanel = new JPanel();
        orderTablePanel = new JPanel();
        placeOrderPanel = new JPanel();
        tablesPanel = new JPanel();
        itemDialog = new EditItemDialog((JFrame) SwingUtilities.getWindowAncestor(this));
        orderPlacedDialog = new OrderPlacedDialog((JFrame) SwingUtilities.getWindowAncestor(this));
    }

    @Override
    protected void setMainLayout() {
        setLayout(new GridBagLayout());
        GridBagConstraints gcMainPanel = new GridBagConstraints();
        gcMainPanel.fill = GridBagConstraints.HORIZONTAL;

        gcMainPanel.ipady = 5;
        gcMainPanel.gridy = 0;
        gcMainPanel.weightx = 0.5;
        gcMainPanel.weighty = 0.1;

        gcMainPanel.gridx = 0;
        gcMainPanel.anchor = GridBagConstraints.PAGE_START;
        add(searchPanel, gcMainPanel);

        gcMainPanel.ipady = 0;
        gcMainPanel.gridy++;
        gcMainPanel.weightx = 0.5;
        gcMainPanel.weighty = 0.1;

        gcMainPanel.gridx = 0;
        gcMainPanel.anchor = GridBagConstraints.LINE_START;
        add(tablesPanel, gcMainPanel);

        gcMainPanel.ipady = 0;
        gcMainPanel.gridy++;
        gcMainPanel.weightx = 0.5;
        gcMainPanel.weighty = 0.1;

        gcMainPanel.gridx = 0;
        gcMainPanel.anchor = GridBagConstraints.PAGE_END;
        add(placeOrderPanel, gcMainPanel);
    }

    @Override
    protected void setTableLayout() {
        setItemsTable();
        setOrderTable();

        tablesPanel.setLayout(new GridBagLayout());
        GridBagConstraints gcTablesPanel = new GridBagConstraints();
        gcTablesPanel.fill = GridBagConstraints.BOTH;

        gcTablesPanel.ipady = 10;
        gcTablesPanel.gridx = 0;
        gcTablesPanel.gridy = 0;
        gcTablesPanel.weightx = 1;
        gcTablesPanel.weighty = 1;
        tablesPanel.add(itemsTablePanel, gcTablesPanel);

        gcTablesPanel.gridx = 0;
        gcTablesPanel.gridy = 1;
        gcTablesPanel.weightx = 1;
        gcTablesPanel.weighty = 1;
        tablesPanel.add(orderTablePanel, gcTablesPanel);
    }

    protected void setItemsTable() {
        Dimension dim = new Dimension(300, 200);
        scrollItemsTable.setPreferredSize(dim);

        itemsTablePanel.setBorder(BorderFactory.createTitledBorder("Browse items - Double click the Item to add it to the order"));

        itemsTablePanel.setLayout(new GridBagLayout());
        GridBagConstraints gcTablePanel = new GridBagConstraints();
        gcTablePanel.fill = GridBagConstraints.HORIZONTAL;

        gcTablePanel.gridx = 0;
        gcTablePanel.gridy = 0;
        gcTablePanel.weightx = 1;
        gcTablePanel.weighty = 0.1;
        gcTablePanel.anchor = GridBagConstraints.FIRST_LINE_START;
        itemsTablePanel.add(scrollItemsTable, gcTablePanel);
    }

    protected void setOrderTable() {
        Dimension dim = new Dimension(300, 200);
        scrollOrderTable.setPreferredSize(dim);

        orderTablePanel.setBorder(BorderFactory.createTitledBorder("Order details - Double click the Item to remove it from the order"));

        orderTablePanel.setLayout(new GridBagLayout());
        GridBagConstraints gcTablePanel = new GridBagConstraints();
        gcTablePanel.fill = GridBagConstraints.HORIZONTAL;

        gcTablePanel.gridx = 0;
        gcTablePanel.gridy = 0;
        gcTablePanel.weightx = 1;
        gcTablePanel.weighty = 0.1;
        gcTablePanel.anchor = GridBagConstraints.FIRST_LINE_START;
        orderTablePanel.add(scrollOrderTable, gcTablePanel);
    }

    @Override
    protected void setSearchPanelLayout() {
        setComboBoxes();

        searchPanel.setBorder(BorderFactory.createTitledBorder("Orders"));
        searchPanel.setLayout(new GridBagLayout());
        GridBagConstraints gcSearchPanel = new GridBagConstraints();
        gcSearchPanel.fill = GridBagConstraints.CENTER;
        gcSearchPanel.insets = new Insets(5, 5, 5, 5);

        /////// First row ///////
        gcSearchPanel.gridy = 0;
        gcSearchPanel.weightx = 0.5;
        gcSearchPanel.weighty = 0.1;

        gcSearchPanel.gridx = 0;
        gcSearchPanel.anchor = GridBagConstraints.FIRST_LINE_END;
        searchPanel.add(providerLabel, gcSearchPanel);

        gcSearchPanel.gridx = 1;
        gcSearchPanel.anchor = GridBagConstraints.FIRST_LINE_START;
        searchPanel.add(providersList, gcSearchPanel);

        gcSearchPanel.gridx = 2;
        gcSearchPanel.anchor = GridBagConstraints.FIRST_LINE_END;
        searchPanel.add(categoryLabel, gcSearchPanel);

        gcSearchPanel.gridx = 3;
        gcSearchPanel.anchor = GridBagConstraints.FIRST_LINE_START;
        searchPanel.add(categoryList, gcSearchPanel);

        gcSearchPanel.gridx = 4;
        gcSearchPanel.anchor = GridBagConstraints.FIRST_LINE_START;
        searchPanel.add(searchItemButton, gcSearchPanel);

        /////// Next row ///////
        gcSearchPanel.gridy++;

        gcSearchPanel.weightx = 0.5;
        gcSearchPanel.weighty = 0.1;

        gcSearchPanel.gridx = 0;
        gcSearchPanel.anchor = GridBagConstraints.FIRST_LINE_END;
        searchPanel.add(itemNameLabel, gcSearchPanel);

        gcSearchPanel.gridx = 1;
        gcSearchPanel.anchor = GridBagConstraints.FIRST_LINE_START;
        searchPanel.add(itemNameTF, gcSearchPanel);

        gcSearchPanel.gridx = 2;
        gcSearchPanel.anchor = GridBagConstraints.FIRST_LINE_END;
        searchPanel.add(unitsLabel, gcSearchPanel);

        gcSearchPanel.gridx = 3;
        gcSearchPanel.anchor = GridBagConstraints.FIRST_LINE_START;
        searchPanel.add(unitsTF, gcSearchPanel);

        gcSearchPanel.gridx = 4;
        gcSearchPanel.anchor = GridBagConstraints.FIRST_LINE_START;

        oneRequired.setForeground(Color.red);
        oneRequired.setVisible(false);
        searchPanel.add(oneRequired, gcSearchPanel);

        searchCompleted.setForeground(Color.blue);
        searchCompleted.setVisible(false);
        searchPanel.add(searchCompleted, gcSearchPanel);

        noResults.setForeground(Color.blue);
        noResults.setVisible(false);
        searchPanel.add(noResults, gcSearchPanel);

        alignFieldSizes();
    }

    @Override
    protected void alignFieldSizes() {
        Dimension fieldSize = unitsTF.getPreferredSize();
        providersList.setPreferredSize(fieldSize);
        categoryList.setPreferredSize(fieldSize);
        searchItemButton.setPreferredSize(fieldSize);
    }

    @Override
    protected void setComboBoxes() {
        setCurrentProvider();
        setCurrentCategories();
        DefaultComboBoxModel providersModel = new DefaultComboBoxModel(providers);
        providersList.setModel(providersModel);
        DefaultComboBoxModel categoryModel = new DefaultComboBoxModel(categories);
        categoryList.setModel(categoryModel);
    }

    private void setCurrentProvider() {
        providers = getAllProviderCompanyName();
        providers.add(0, GUIConstants.SELECT_FIELD);
    }

    private void setCurrentCategories() {
        categories = getAllCategoryNames();
        categories.add(0, GUIConstants.SELECT_FIELD);
    }

    protected void setPlaceOrderLayout() {
        placeOrderPanel.setLayout(new GridBagLayout());
        placeOrderPanel.setBorder(BorderFactory.createSoftBevelBorder(BevelBorder.RAISED));
        GridBagConstraints gcPlaceOrderPanel = new GridBagConstraints();
        gcPlaceOrderPanel.fill = GridBagConstraints.CENTER;
        gcPlaceOrderPanel.insets = new Insets(5, 5, 5, 5);

        /////// First row ///////
        Common.FirstRow(gcPlaceOrderPanel);


        /////// Next row //////
        Common.NextRow(gcPlaceOrderPanel,0.5,0.1,0);
        gcPlaceOrderPanel.anchor = GridBagConstraints.FIRST_LINE_END;
        placeOrderPanel.add(orderSumLabel, gcPlaceOrderPanel);

        gcPlaceOrderPanel.gridx = 1;
        gcPlaceOrderPanel.anchor = GridBagConstraints.FIRST_LINE_START;
        placeOrderPanel.add(orderSumFieldLabel, gcPlaceOrderPanel);

        gcPlaceOrderPanel.gridx = 2;
        gcPlaceOrderPanel.anchor = GridBagConstraints.FIRST_LINE_START;
        placeOrderPanel.add(placeOrderButton, gcPlaceOrderPanel);

        gcPlaceOrderPanel.gridx = 3;
        gcPlaceOrderPanel.anchor = GridBagConstraints.FIRST_LINE_START;
        placeOrderErrorLabel.setVisible(false);
        placeOrderErrorLabel.setForeground(Color.red);
        placeOrderPanel.add(placeOrderErrorLabel, gcPlaceOrderPanel);

        ///// align fields sizes //////
        Dimension fieldSize = unitsTF.getPreferredSize();
        orderSumFieldLabel.setPreferredSize(fieldSize);
        placeOrderButton.setPreferredSize(fieldSize);
    }

    @Override
    protected void setActionListeners() {
        setSearchButton();
        setPlaceOrderButton();
        setEditUnitsListener();
        setAddItemDialogListener();
        setRemoveItemFromOrderListener();
    }

    private void setEditUnitsListener() {
        itemsTable.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent mouseEvent) {
                JTable table = (JTable) mouseEvent.getSource();
                Point point = mouseEvent.getPoint();
                int row = table.rowAtPoint(point);
                if (mouseEvent.getClickCount() == 2 && table.getSelectedRow() != -1) {
                    orderItemId = itemsTable.getValueAt(row, 0).toString();
                    itemDialog.numOfItemsTF.setText("");
                    itemDialog.setVisible(true);
                }
            }
        });
    }

    //Used to pass information from Dialog back to the Panel
    private void setAddItemDialogListener() {
        itemDialog.setItemDialogListener(new DialogListener() {
            @Override
            public void setItemInOrder(int units) {
                orderItemAmount = units;
                System.out.println(orderItemAmount);
                String[] productToAdd = convertProductToOrderArr(productManager.getProductByProductId(orderItemId));
                ordersTableModel.addRow(productToAdd);
                orderSum += calculateItemSum(productToAdd[4], productToAdd[5]); //update the order sum by the price
                setOrderSumFieldLabel(); //updates the sum label
            }
        });
    }

    private void setRemoveItemFromOrderListener() {
        orderTable.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent mouseEvent) {
                JTable table = (JTable) mouseEvent.getSource();
                Point point = mouseEvent.getPoint();
                int row = table.rowAtPoint(point);
                if (mouseEvent.getClickCount() == 2 && table.getSelectedRow() != -1) {
                    orderSum -= calculateItemSum(ordersTableModel.getValueAt(row, 4).toString(), ordersTableModel.getValueAt(row, 5).toString());
                    setOrderSumFieldLabel(); //updates the sum label
                    ordersTableModel.removeRow(row);
                }
            }
        });
    }

    private int calculateItemSum(String numOfItems, String itemPrice) {
        int numItems = valueOf(numOfItems), price = valueOf(itemPrice);
        return numItems * price;
    }

    private void setOrderSumFieldLabel() {
        orderSumFieldLabel.setText(orderSum.toString());
    }

    private void setPlaceOrderButton() {
        placeOrderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Vector<Vector> data = ordersTableModel.getDataVector();
                Order order = new Order();
                order.setOrderedProducts(data, 0, 4);
                order.setTotalAmount(orderSum.doubleValue());
                order.setOrderStatus(OrderStatus.inProcess);
                order.setOrderDate(new Date(System.currentTimeMillis()));
                order.setDeliveryDate(new Date(System.currentTimeMillis()));
                order.setOrderId(orderManager.AddOrder(order));
                if (order.getOrderId() == -1)
                    placeOrderErrorLabel.setVisible(true);
                else {
                    //passing the order id to the dialog
                    orderPlacedDialog.setPlacedOrderId(order.getOrderId());
                    orderPlacedDialog.setVisible(true);
                }
            }
        });
    }

    private void setSearchButton() {
        searchItemButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (checkAtleastOneNotEmpty()) {
                    setValidationLabelsVisibility(false);
                    Vector<Product> x = productManager.getListOfProducts(buildSearchParameters());
                    if (x.size() == 0) {
                        itemsTableModel.setDataVector(convertProductVectorToProductMatrix(x), itemsColumnNames);
                        noResults.setVisible(true);
                    } else {
                        setValidationLabelsVisibility(false);
                        itemsTableModel.setDataVector(convertProductVectorToProductMatrix(x), itemsColumnNames);
                        searchCompleted.setVisible(true);
                    }
                } else {
                    setValidationLabelsVisibility(false);
                    oneRequired.setVisible(true);
                }

            }
        });
    }

    //"Item name","Category","Provider","Available units","Price per Item","Expiration date"
    private String[][] convertProductVectorToProductMatrix(Vector<Product> productVector) {
        String[][] matrix = new String[productVector.size()][Constants.PRODUCTS_MATRIX_COLUMNS];
        for (int i = 0; i < productVector.size(); i++) {
            String[] array = {
                    productVector.get(i).getProductId(),
                    productVector.get(i).getProductName(),
                    String.valueOf(productVector.get(i).getCategory()),
                    providerManaging.getProviderNameById(productVector.get(i).getProviderId()),
                    String.valueOf(productVector.get(i).getCurrentProductAmount()),
                    productVector.get(i).getPrice(),
                    DateUtils.formatDateToString(productVector.get(i).getExpirationDate())
            };
            matrix[i] = array;
        }

        return matrix;
    }

    //{"ID", "Item name", "Category", "Provider", "Selected units", "Expiration date"};
    private String[] convertProductToOrderArr(Product product) {
        String[] productArr = {
                product.getProductId(),
                product.getProductName(),
                String.valueOf(product.getCategory()),
                providerManaging.getProviderNameById(product.getProviderId()),
                String.valueOf(orderItemAmount),
                product.getPrice(),
                DateUtils.formatDateToString(product.getExpirationDate())
        };
        return productArr;
    }

    private HashMap buildSearchParameters() {
        HashMap searchParams = new HashMap();
        if (!providersList.getSelectedItem().equals(GUIConstants.SELECT_FIELD))
            searchParams.put("product." + DatabaseConstants.PRODUCT_TABLE_ITEM_PROVIDER_COLUMN, providerManaging.getProviderIdByName(StringUtils.getStringWithSingleQuotes(providersList.getSelectedItem().toString())));

        if (!categoryList.getSelectedItem().equals(GUIConstants.SELECT_FIELD))
            searchParams.put(DatabaseConstants.PRODUCT_TABLE_ITEM_CATEGORY_COLUMN, StringUtils.getStringWithSingleQuotes(categoryList.getSelectedItem().toString()));

        if (!itemNameTF.getText().equals(GUIConstants.EMPTY_FIELD))
            searchParams.put(DatabaseConstants.PRODUCT_TABLE_ITEM_NAME_COLUMN, StringUtils.getStringWithSingleQuotes(itemNameTF.getText()));

        if (!unitsTF.getText().equals(GUIConstants.EMPTY_FIELD))
            searchParams.put(DatabaseConstants.PRODUCT_TABLE_ITEM_CURRENT_AMOUNT_COLUMN, unitsTF.getText());
        return searchParams;
    }

    private boolean checkAtleastOneNotEmpty() {
        if (!providersList.getSelectedItem().equals(GUIConstants.SELECT_FIELD) ||
                !categoryList.getSelectedItem().equals(GUIConstants.SELECT_FIELD) ||
                !itemNameTF.getText().equals(GUIConstants.EMPTY_FIELD) ||
                !unitsTF.getText().equals(GUIConstants.EMPTY_FIELD)
        )
            return true;

        else
            return false;
    }

    @Override
    protected void setValidationLabelsVisibility(boolean visibility) {
        oneRequired.setVisible(visibility);
        searchCompleted.setVisible(visibility);
        noResults.setVisible(visibility);
        placeOrderErrorLabel.setVisible(visibility);
    }
}
