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
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Vector;

import static java.lang.String.valueOf;
import static main.java.database.DatabaseController.getAllCategoryNames;
import static main.java.database.DatabaseController.getAllProviderCompanyName;

public class OrdersSearchPanel extends IWorkPanel {
    private JLabel providerLabel;
    private JLabel categoryLabel;
    private JLabel itemNameLabel;
    private JLabel orderIdLabel;
    private JLabel orderStatusLabel;
    private JLabel deliveryDateLabel;
    private JLabel orderDateLabel;
    private JLabel oneRequired;
    private JLabel noResults;
    private JLabel searchCompleted;
    private JComboBox providersList;
    private JComboBox categoryList;
    private JTextField orderIdTF;
    private JTextField itemNameTF;
    private JCheckBox openOrderCB;
    private JCheckBox closedOrderCB;
    private JDateChooser deliveryDateChooser;
    private JDateChooser orderDateChooser;
    private JButton searchOrderButton;
    private JTable ordersTable;
    private JTable itemsTable;
    private DefaultTableModel itemsTableModel;
    private DefaultTableModel ordersTableModel;
    private JScrollPane scrollOrdersTable;
    private JScrollPane scrollItemsTable;
    private JPanel searchPanel;
    private JPanel ordersTablePanel;
    private JPanel itemsTablePanel;
    private JPanel tablesPanel;
    private Vector<String> providers;
    private Vector<String> categories;

    //Table Column FIELDS//
    private String[] itemsColumnNames = {"Item name","Category","Provider","Ordered Amount","Cost"};
    private String[] ordersColumnNames = {"Order ID","Total amount","Order status","Order Date","Delivery Date"};

    public OrdersSearchPanel(){
            initialization();
            setSearchPanelLayout();
            setTableLayout();
            setMainLayout();
            setActionListeners();
    }

    @Override
    protected void initialization(){
        providerLabel = new JLabel(GUIConstants.PROVIDER);
        categoryLabel = new JLabel(GUIConstants.CATEGORY);
        itemNameLabel = new JLabel(GUIConstants.ITEM_NAME);
        orderIdLabel = new JLabel(GUIConstants.ORDER_ID);
        orderStatusLabel = new JLabel(GUIConstants.ORDER_STATUS);
        deliveryDateLabel = new JLabel(GUIConstants.DELIVERY_DATE);
        orderDateLabel = new JLabel(GUIConstants.ORDER_DATE);
        oneRequired = new JLabel(GUIConstants.ATLEAST_ONE_FIELD_REQUIRED);
        noResults = new JLabel(GUIConstants.NO_RESULTS);
        searchCompleted = new JLabel(GUIConstants.SEARCH_COMPLETED);
        providersList = new JComboBox();
        categoryList = new JComboBox();
        openOrderCB = new JCheckBox("In Process");
        closedOrderCB = new JCheckBox("Delivered");
        deliveryDateChooser = new JDateChooser();
        orderDateChooser = new JDateChooser();
        itemNameTF = new JTextField(10);
        orderIdTF = new JTextField(10);
        searchOrderButton = new JButton(GUIConstants.SEARCH_ORDER);
        itemsTableModel = new DefaultTableModel(null, itemsColumnNames);
        itemsTable = new JTable(itemsTableModel){public boolean isCellEditable(int row, int column){
                return false;
            }};
        ordersTableModel = new DefaultTableModel(null,ordersColumnNames);
        ordersTable = new JTable(ordersTableModel){ public boolean isCellEditable(int row, int column){return false; }};
        scrollOrdersTable = new JScrollPane(ordersTable,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollItemsTable = new JScrollPane(itemsTable,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        searchPanel = new JPanel();
        ordersTablePanel = new JPanel();
        itemsTablePanel = new JPanel();
        tablesPanel = new JPanel();
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
        gcMainPanel.gridy ++;
        gcMainPanel.weightx = 0.5;
        gcMainPanel.weighty = 0.1;

        gcMainPanel.gridx = 0;
        gcMainPanel.anchor = GridBagConstraints.LINE_START;
        add(tablesPanel, gcMainPanel);
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
        tablesPanel.add(ordersTablePanel,gcTablesPanel);

        gcTablesPanel.gridx = 0;
        gcTablesPanel.gridy = 1;
        gcTablesPanel.weightx = 1;
        gcTablesPanel.weighty = 1;
        tablesPanel.add(itemsTablePanel,gcTablesPanel);
    }

    private void setOrderTable() {
        Dimension dim = new Dimension(300,200);
        scrollOrdersTable.setPreferredSize(dim);

        ordersTablePanel.setLayout(new GridBagLayout());
        ordersTablePanel.setBorder(BorderFactory.createTitledBorder("Browse orders"));
        GridBagConstraints gcTablePanel = new GridBagConstraints();
        gcTablePanel.fill = GridBagConstraints.HORIZONTAL;

        gcTablePanel.gridx = 0;
        gcTablePanel.gridy = 0;
        gcTablePanel.weightx = 1;
        gcTablePanel.weighty = 0.1;
        gcTablePanel.anchor = GridBagConstraints.FIRST_LINE_START;
        ordersTablePanel.add(scrollOrdersTable,gcTablePanel);
    }

    private void setItemsTable() {
        Dimension dim = new Dimension(300,200);
        scrollItemsTable.setPreferredSize(dim);

        itemsTablePanel.setLayout(new GridBagLayout());
        itemsTablePanel.setBorder(BorderFactory.createTitledBorder("Browse order items"));
        GridBagConstraints gcTablePanel = new GridBagConstraints();
        gcTablePanel.fill = GridBagConstraints.HORIZONTAL;

        gcTablePanel.gridx = 0;
        gcTablePanel.gridy = 0;
        gcTablePanel.weightx = 1;
        gcTablePanel.weighty = 0.1;
        gcTablePanel.anchor = GridBagConstraints.FIRST_LINE_START;
        itemsTablePanel.add(scrollItemsTable,gcTablePanel);
    }

    @Override
    protected void setSearchPanelLayout() {

        setComboBoxes();

        searchPanel.setBorder(BorderFactory.createTitledBorder("Orders"));
        searchPanel.setLayout(new GridBagLayout());
        GridBagConstraints gcSearchPanel = new GridBagConstraints();
        gcSearchPanel.fill = GridBagConstraints.CENTER;
        gcSearchPanel.insets = new Insets(5,5,5,5);

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
        searchPanel.add(searchOrderButton, gcSearchPanel);

        /////// Next row ///////
        gcSearchPanel.gridy ++;

        gcSearchPanel.weightx = 0.5;
        gcSearchPanel.weighty = 0.1;

        gcSearchPanel.gridx = 0;
        gcSearchPanel.anchor = GridBagConstraints.FIRST_LINE_END;
        searchPanel.add(orderDateLabel, gcSearchPanel);

        gcSearchPanel.gridx = 1;
        gcSearchPanel.anchor = GridBagConstraints.FIRST_LINE_START;
        searchPanel.add(orderDateChooser, gcSearchPanel);

        gcSearchPanel.gridx = 2;
        gcSearchPanel.anchor = GridBagConstraints.FIRST_LINE_END;
        searchPanel.add(deliveryDateLabel, gcSearchPanel);

        gcSearchPanel.gridx = 3;
        gcSearchPanel.anchor = GridBagConstraints.FIRST_LINE_START;
        searchPanel.add(deliveryDateChooser, gcSearchPanel);

        /////// Next row ///////
        gcSearchPanel.gridy ++;

        gcSearchPanel.weightx = 0.5;
        gcSearchPanel.weighty = 0.1;

        gcSearchPanel.gridx = 0;
        gcSearchPanel.anchor = GridBagConstraints.FIRST_LINE_END;
        searchPanel.add(orderIdLabel, gcSearchPanel);

        gcSearchPanel.gridx = 1;
        gcSearchPanel.anchor = GridBagConstraints.FIRST_LINE_START;
        searchPanel.add(orderIdTF, gcSearchPanel);

        gcSearchPanel.gridx = 2;
        gcSearchPanel.anchor = GridBagConstraints.FIRST_LINE_END;
        searchPanel.add(orderStatusLabel, gcSearchPanel);

        gcSearchPanel.gridx = 3;
        gcSearchPanel.anchor = GridBagConstraints.FIRST_LINE_START;
        searchPanel.add(openOrderCB, gcSearchPanel);

        /////// Next row ///////
        gcSearchPanel.gridy ++;
        gcSearchPanel.weightx = 0.1;
        gcSearchPanel.weighty = 0.1;

        gcSearchPanel.gridx = 0;
        gcSearchPanel.anchor = GridBagConstraints.FIRST_LINE_END;
        searchPanel.add(itemNameLabel, gcSearchPanel);

        gcSearchPanel.gridx = 1;
        gcSearchPanel.anchor = GridBagConstraints.FIRST_LINE_START;
        searchPanel.add(itemNameTF, gcSearchPanel);

        gcSearchPanel.gridx = 3;
        gcSearchPanel.anchor = GridBagConstraints.FIRST_LINE_START;
        searchPanel.add(closedOrderCB, gcSearchPanel);

        gcSearchPanel.gridx = 4;
        gcSearchPanel.anchor = GridBagConstraints.FIRST_LINE_START;

        searchPanel.add(oneRequired, gcSearchPanel);
        oneRequired.setForeground(Color.red);
        oneRequired.setVisible(false);

        searchCompleted.setForeground(Color.blue);
        searchCompleted.setVisible(false);
        searchPanel.add(searchCompleted, gcSearchPanel);

        noResults.setForeground(Color.blue);
        noResults.setVisible(false);
        searchPanel.add(noResults, gcSearchPanel);

        alignFieldSizes();
    }

    @Override
    protected void setComboBoxes() {
        setCurrentProvider();//TODO: should be adjusted live and not only when running the app first
        setCurrentCategories(); //TODO: same here
        DefaultComboBoxModel providersModel = new DefaultComboBoxModel(providers);
        providersList.setModel(providersModel);
        DefaultComboBoxModel categoryModel = new DefaultComboBoxModel(categories);
        categoryList.setModel(categoryModel);
    }

    @Override
    protected void alignFieldSizes(){
        Dimension fieldSize = orderIdTF.getPreferredSize();
        providersList.setPreferredSize(fieldSize);
        categoryList.setPreferredSize(fieldSize);
        searchOrderButton.setPreferredSize(fieldSize);
        deliveryDateChooser.setPreferredSize(fieldSize);
        orderDateChooser.setPreferredSize(fieldSize);
    }

    private void setCurrentProvider() {
        providers = getAllProviderCompanyName();
        providers.add(0, GUIConstants.SELECT_FIELD);
    }

    private void setCurrentCategories(){
        categories = getAllCategoryNames();
        categories.add(0, GUIConstants.SELECT_FIELD);
    }

    @Override
    protected void setActionListeners(){
        setSearchButton();
        setOrderSelectFromTable();
    }

    private void setOrderSelectFromTable() {
        ordersTable.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
            @Override
            public void valueChanged(ListSelectionEvent event) {
                if(ordersTable.getSelectedRow() >= 0) {
                    String orderId = ordersTable.getValueAt(ordersTable.getSelectedRow(), 0).toString();
                    Vector<Product> productList = DatabaseController.getListOfOrderedProductsByOrder(StringUtils.getStringWithSingleQuotes(orderId));
                    itemsTableModel.setDataVector(convertProductVectorToOrderedItemsMatrix(productList), itemsColumnNames);
                }
            }
        });
    }

    private void setSearchButton() {
        searchOrderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(checkAtleastOneNotEmpty()){
                    setValidationLabelsVisibility(false);
                    Vector<Order> x = DatabaseController.getListOfOrders(buildSearchParameters());
                    if(x.size() == 0) {
                        ordersTableModel.setDataVector(convertOrderVectorToOrderMatrix(x), ordersColumnNames);
                        itemsTableModel.setDataVector(null,itemsColumnNames);
                        noResults.setVisible(true);
                    }
                    else{
                        setValidationLabelsVisibility(false);
                        ordersTableModel.setDataVector(convertOrderVectorToOrderMatrix(x), ordersColumnNames);
                        itemsTableModel.setDataVector(null,itemsColumnNames);
                        searchCompleted.setVisible(true);
                    }
                }
                else{
                    setValidationLabelsVisibility(false);
                    oneRequired.setVisible(true);
                }

            }
        });
    }

    //{"Order ID","Provider ID","Total amount","Order status","Order Date","Delivery Date"};
    private String[][] convertOrderVectorToOrderMatrix(Vector<Order> orderVector) {
        String[][] matrix = new String[orderVector.size()][Constants.ORDER_MATRIX_COLUMNS];
        for (int i = 0; i < orderVector.size(); i++) {
            String[] array = {
                    orderVector.get(i).getOrderId().toString(),
                    orderVector.get(i).getTotalAmount().toString(),
                    String.valueOf(orderVector.get(i).getOrderStatus()),
                    DateUtils.formatDateToString(orderVector.get(i).getOrderDate()),
                    DateUtils.formatDateToString(orderVector.get(i).getDeliveryDate())
            };
            matrix[i] = array;
        }

        return matrix;
    }

    //"Item name","Category","Provider","Ordered Amount","Price"
    private String[][] convertProductVectorToOrderedItemsMatrix(Vector<Product> productVector) {
        String[][] matrix = new String[productVector.size()][Constants.ORDERED_ITEMS_MATRIX_COLUMNS];
        for (int i = 0; i < productVector.size(); i++) {
            String[] array = {
                    productVector.get(i).getProductName(),
                    String.valueOf(productVector.get(i).getCategory()),
                    DatabaseController.getProviderNameById(productVector.get(i).getProviderId()),
                    String.valueOf(productVector.get(i).getCurrentProductAmount()),
                    productVector.get(i).getPrice()
            };
            matrix[i] = array;
        }

        return matrix;
    }

    //search query will JOIN ordered_items, product, orders
    private HashMap buildSearchParameters() {
        HashMap searchParams = new HashMap();
        if(!providersList.getSelectedItem().equals(GUIConstants.SELECT_FIELD))
            searchParams.put("product."+DatabaseConstants.PRODUCT_TABLE_ITEM_PROVIDER_COLUMN, DatabaseController.getProviderIdByName(StringUtils.getStringWithSingleQuotes(providersList.getSelectedItem().toString())));

        if (!categoryList.getSelectedItem().equals(GUIConstants.SELECT_FIELD))
            searchParams.put(DatabaseConstants.PRODUCT_TABLE_ITEM_CATEGORY_COLUMN, StringUtils.getStringWithSingleQuotes(categoryList.getSelectedItem().toString()));

        if(!itemNameTF.getText().equals(GUIConstants.EMPTY_FIELD))
            searchParams.put(DatabaseConstants.PRODUCT_TABLE_ITEM_NAME_COLUMN,itemNameTF.getText());

        if(!orderIdTF.getText().equals(GUIConstants.EMPTY_FIELD))
            searchParams.put(DatabaseConstants.ORDERED_ITEMS_TABLE_ORDER_ID_COLUMN,orderIdTF.getText());

        if(orderDateChooser.getDate() != null)
            searchParams.put(DatabaseConstants.ORDERS_TABLE_ORDER_DATE_COLUMN, StringUtils.getStringWithSingleQuotes(DateUtils.formatDateToString(orderDateChooser.getDate())));

        if(deliveryDateChooser.getDate() != null)
            searchParams.put(DatabaseConstants.ORDERS_TABLE_DELIVERY_DATE_COLUMN,StringUtils.getStringWithSingleQuotes(DateUtils.formatDateToString(deliveryDateChooser.getDate())));

        if(closedOrderCB.isSelected())
            searchParams.put(DatabaseConstants.ORDERS_TABLE_ORDER_STATUS_COLUMN, OrderStatus.valueOf("delivered"));

        if(openOrderCB.isSelected())
            searchParams.put(DatabaseConstants.ORDERS_TABLE_ORDER_STATUS_COLUMN,OrderStatus.valueOf("inProcess"));
        return searchParams;
    }

    private boolean checkAtleastOneNotEmpty() {
        if(     !providersList.getSelectedItem().equals(GUIConstants.SELECT_FIELD) ||
                !categoryList.getSelectedItem().equals(GUIConstants.SELECT_FIELD) ||
                !itemNameTF.getText().equals(GUIConstants.EMPTY_FIELD )||
                !orderIdTF.getText().equals(GUIConstants.EMPTY_FIELD )||
                orderDateChooser.getDate() != null ||
                deliveryDateChooser.getDate() != null ||
                closedOrderCB.isSelected() ||
                openOrderCB.isSelected()
        )
            return true;

        else
            return false;
    }

    @Override
    protected void setValidationLabelsVisibility(boolean visibility){
        oneRequired.setVisible(visibility);
        searchCompleted.setVisible(visibility);
        noResults.setVisible(visibility);
    }

}
