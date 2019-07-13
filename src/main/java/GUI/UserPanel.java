package main.java.GUI;

import com.toedter.calendar.JDateChooser;
import main.java.BL.Contract.Order;
import main.java.BL.Contract.OrderStatus;
import main.java.BL.Contract.Product;
import main.java.common.DateUtils;
import main.java.common.StringUtils;
import main.java.common.constants.Constants;
import main.java.common.constants.DatabaseConstants;
import main.java.common.constants.GUIConstants;
import main.java.database.DatabaseController;

import javax.swing.*;
import javax.swing.border.BevelBorder;
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

public class UserPanel extends IWorkPanel {
    private JLabel userNameLable;
    private JLabel firstnameLable;
    private JLabel lastNameLable;
    private JLabel dateOfBirthLable;
    private JLabel phoneNumberLable;
    private JLabel isManagerLable;
    private JLabel departmentLable;
    private JLabel hireDateLable;
    private JComboBox department;
    private JTextField userName;
    private JTextField firstName;
    private JTextField phoneNumber;
    private JTextField lastName;
    private JCheckBox isManager;

    private JDateChooser dateOfBirth;
    private JButton addUserButton;
    private JScrollPane scrollItemsTable;
    private JScrollPane scrollOrderTable;
    private JPanel searchPanel;
    private EditItemDialog itemDialog;
    private OrderPlacedDialog orderPlacedDialog;
    private Integer orderSum = 0;
    private Vector<String> providers;
    private Vector<String> categories;
    private String orderItemId;
    private int orderItemAmount;


    public UserPanel() {
        initialization();
        setSearchPanelLayout();
        setTableLayout();
        setMainLayout();
        setActionListeners();
    }

    @Override
    protected void initialization() {
        userNameLable = new JLabel("User Name");
        firstnameLable = new JLabel("First Name");
        lastNameLable = new JLabel("Last Name");
        dateOfBirthLable = new JLabel("Fite");
        phoneNumberLable = new JLabel(GUIConstants.ZERO);
        isManagerLable = new JLabel(GUIConstants.ATLEAST_ONE_FIELD_REQUIRED);
        departmentLable = new JLabel(GUIConstants.NO_RESULTS);
        hireDateLable = new JLabel(GUIConstants.SEARCH_COMPLETED);
        department = new JComboBox();
        userName = new JTextField(10);
        firstName = new JTextField(10);
        addUserButton = new JButton("Add User");
        searchPanel = new JPanel();
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

    }

    @Override
    protected void setTableLayout() {

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
        searchPanel.add(userNameLable, gcSearchPanel);

        gcSearchPanel.gridx = 1;
        gcSearchPanel.anchor = GridBagConstraints.FIRST_LINE_START;
        searchPanel.add(userName, gcSearchPanel);

        gcSearchPanel.gridx = 2;
        gcSearchPanel.anchor = GridBagConstraints.FIRST_LINE_END;
        searchPanel.add(firstnameLable, gcSearchPanel);

        gcSearchPanel.gridx = 3;
        gcSearchPanel.anchor = GridBagConstraints.FIRST_LINE_START;
        searchPanel.add(firstName, gcSearchPanel);

        gcSearchPanel.gridx = 4;
        gcSearchPanel.anchor = GridBagConstraints.FIRST_LINE_START;
        searchPanel.add(firstName, gcSearchPanel);

        /////// Next row ///////
        gcSearchPanel.gridy++;

        gcSearchPanel.weightx = 0.5;
        gcSearchPanel.weighty = 0.1;

        gcSearchPanel.gridx = 0;
        gcSearchPanel.anchor = GridBagConstraints.FIRST_LINE_END;
        searchPanel.add(lastNameLable, gcSearchPanel);

        gcSearchPanel.gridx = 1;
        gcSearchPanel.anchor = GridBagConstraints.FIRST_LINE_START;
        searchPanel.add(lastName, gcSearchPanel);

        gcSearchPanel.gridx = 2;
        gcSearchPanel.anchor = GridBagConstraints.FIRST_LINE_END;
        searchPanel.add(dateOfBirthLable, gcSearchPanel);

        gcSearchPanel.gridx = 3;
        gcSearchPanel.anchor = GridBagConstraints.FIRST_LINE_START;
        searchPanel.add(dateOfBirth, gcSearchPanel);

        gcSearchPanel.gridx = 4;
        gcSearchPanel.anchor = GridBagConstraints.FIRST_LINE_START;
        searchPanel.add(departmentLable, gcSearchPanel);

        gcSearchPanel.gridx = 5;
        gcSearchPanel.anchor = GridBagConstraints.FIRST_LINE_START;
        searchPanel.add(department, gcSearchPanel);
/////// Next row ///////
        gcSearchPanel.gridy++;

        gcSearchPanel.weightx = 0.5;
        gcSearchPanel.weighty = 0.1;

        gcSearchPanel.gridx = 0;
        gcSearchPanel.anchor = GridBagConstraints.FIRST_LINE_START;
        searchPanel.add(addUserButton, gcSearchPanel);

        alignFieldSizes();
    }

    @Override
    protected void alignFieldSizes() {
        Dimension fieldSize = userName.getPreferredSize();
        department.setPreferredSize(fieldSize);
        addUserButton.setPreferredSize(fieldSize);
    }

    @Override
    protected void setComboBoxes() {
        setCurrentProvider();//TODO: should be adjusted live and not only when running the app first
        setCurrentCategories(); //TODO: same here
        DefaultComboBoxModel providersModel = new DefaultComboBoxModel(providers);
        department.setModel(providersModel);
        DefaultComboBoxModel categoryModel = new DefaultComboBoxModel(categories);
    }

    private void setCurrentProvider() {
        providers = getAllProviderCompanyName();
        providers.add(0, GUIConstants.SELECT_FIELD);
    }

    private void setCurrentCategories() {
        categories = getAllCategoryNames();
        categories.add(0, GUIConstants.SELECT_FIELD);
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
                String[] productToAdd = convertProductToOrderArr(DatabaseController.getProductByProductId(orderItemId));
                ordersTableModel.addRow(productToAdd);
                orderSum += calculateItemSum(productToAdd[4], productToAdd[5]); //update the order sum by the price (TODO: update the test data)
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

    //TODO: consider moving to a utils class
    private int calculateItemSum(String numOfItems, String itemPrice) {
        int numItems = valueOf(numOfItems), price = valueOf(itemPrice);
        return numItems * price;
    }

    private void setOrderSumFieldLabel() {
        phoneNumberLable.setText(orderSum.toString());
    }

    private void setPlaceOrderButton() {
        addUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Vector<Vector> data = ordersTableModel.getDataVector();
                Order order = new Order();
                order.setOrderedProducts(data, 0, 4);
                order.setTotalAmount(orderSum.doubleValue());
                order.setOrderStatus(OrderStatus.inProcess);
                order.setOrderDate(new Date(System.currentTimeMillis()));
                order.setDeliveryDate(new Date(System.currentTimeMillis()));
                order.setOrderId(DatabaseController.addOrder(order));
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
                    Vector<Product> x = DatabaseController.getListOfProducts(buildSearchParameters());
                    if (x.size() == 0) {
                        itemsTableModel.setDataVector(convertProductVectorToProductMatrix(x), itemsColumnNames);
                        departmentLable.setVisible(true);
                    } else {
                        setValidationLabelsVisibility(false);
                        itemsTableModel.setDataVector(convertProductVectorToProductMatrix(x), itemsColumnNames);
                        hireDateLable.setVisible(true);
                    }
                } else {
                    setValidationLabelsVisibility(false);
                    isManagerLable.setVisible(true);
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
                    DatabaseController.getProviderNameById(productVector.get(i).getProviderId()),
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
                DatabaseController.getProviderNameById(product.getProviderId()),
                String.valueOf(orderItemAmount),
                product.getPrice(),
                DateUtils.formatDateToString(product.getExpirationDate())
        };
        return productArr;
    }

    private HashMap buildSearchParameters() {
        HashMap searchParams = new HashMap();
        if (!department.getSelectedItem().equals(GUIConstants.SELECT_FIELD))
            searchParams.put("product." + DatabaseConstants.PRODUCT_TABLE_ITEM_PROVIDER_COLUMN, DatabaseController.getProviderIdByName(StringUtils.getStringWithSingleQuotes(department.getSelectedItem().toString())));

        if (!categoryList.getSelectedItem().equals(GUIConstants.SELECT_FIELD))
            searchParams.put(DatabaseConstants.PRODUCT_TABLE_ITEM_CATEGORY_COLUMN, StringUtils.getStringWithSingleQuotes(categoryList.getSelectedItem().toString()));

        if (!firstName.getText().equals(GUIConstants.EMPTY_FIELD))
            searchParams.put(DatabaseConstants.PRODUCT_TABLE_ITEM_NAME_COLUMN, StringUtils.getStringWithSingleQuotes(firstName.getText()));

        if (!userName.getText().equals(GUIConstants.EMPTY_FIELD))
            searchParams.put(DatabaseConstants.PRODUCT_TABLE_ITEM_CURRENT_AMOUNT_COLUMN, userName.getText());
        return searchParams;
    }

    private boolean checkAtleastOneNotEmpty() {
        if (!department.getSelectedItem().equals(GUIConstants.SELECT_FIELD) ||
                !categoryList.getSelectedItem().equals(GUIConstants.SELECT_FIELD) ||
                !firstName.getText().equals(GUIConstants.EMPTY_FIELD) ||
                !userName.getText().equals(GUIConstants.EMPTY_FIELD)
        )
            return true;

        else
            return false;
    }

    @Override
    protected void setValidationLabelsVisibility(boolean visibility) {
        isManagerLable.setVisible(visibility);
        hireDateLable.setVisible(visibility);
        departmentLable.setVisible(visibility);
        placeOrderErrorLabel.setVisible(visibility);
    }
}
