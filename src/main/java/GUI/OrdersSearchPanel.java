package main.java.GUI;

import com.toedter.calendar.JDateChooser;
import main.java.BL.Contract.OrderStatus;
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
    //private JComboBox itemList;
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
    private JScrollPane scrollOrdersTable;
    private JScrollPane scrollItemsTable;
    private JPanel searchPanel;
    private JPanel ordersTablePanel;
    private JPanel itemsTablePanel;
    private JPanel tablesPanel;
    private Vector<String> providers;
    private Vector<String> categories;
    private Vector<String> items;

    //TEST FIELDS//
    private HashMap searchParams = new HashMap();
    private String[] itemsColumnNames = {"ID","Item name","Category","Provider","Current amount","Cost","Expiration date"};
    private String[][] items1TestData = {{"1313","kuku","dairy","Shufersal","5","451","15.12.2020"}
                                        ,{"1314","lolo","meat","mega","57","41","21.01.2020"}};
    private String[][] items2TestData = {{"1300","shubu","uniform","castro","5","200","N/A"}};
    private String[] ordersColumnNames = {"Order ID","Provider ID","Total amount","Delivery status","Order Date","Delivery Date"};
    private String[][] order1TestData ={{"555","2","999","Closed","10.05.19","15.05.19"}
                                        ,{"44","1","23","Closed","11.05.19","14.05.19"}};

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
        //itemList = new JComboBox();
        openOrderCB = new JCheckBox("In Process");
        closedOrderCB = new JCheckBox("Delivered");
        deliveryDateChooser = new JDateChooser();
        orderDateChooser = new JDateChooser();
        itemNameTF = new JTextField(10);
        orderIdTF = new JTextField(10);
        searchOrderButton = new JButton(GUIConstants.SEARCH_ORDER);
        itemsTableModel = new DefaultTableModel(null, itemsColumnNames);
        ordersTable = new JTable(order1TestData, ordersColumnNames);
        itemsTable = new JTable(itemsTableModel);
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

    private void setComboBoxes() {
        setCurrentProvider();//TODO: should be adjusted live and not only when running the app first
        setCurrentCategories(); //TODO: same here
        //setCurrentItems();//TODO: same here
        DefaultComboBoxModel providersModel = new DefaultComboBoxModel(providers);
        providersList.setModel(providersModel);
        DefaultComboBoxModel categoryModel = new DefaultComboBoxModel(categories);
        categoryList.setModel(categoryModel);
        //DefaultComboBoxModel itemsModel = new DefaultComboBoxModel(items);
        //itemList.setModel(itemsModel);
    }

    private void alignFieldSizes(){
        Dimension fieldSize = orderIdTF.getPreferredSize();
        providersList.setPreferredSize(fieldSize);
        categoryList.setPreferredSize(fieldSize);
        //itemList.setPreferredSize(fieldSize);
        searchOrderButton.setPreferredSize(fieldSize);
        deliveryDateChooser.setPreferredSize(fieldSize);
        orderDateChooser.setPreferredSize(fieldSize);
    }

    /*
    private void setCurrentItems() {
        items = getAllProductsNames();
        items.add(0,GUIConstants.SELECT_FIELD);
    }
    */
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
                String orderId = ordersTable.getValueAt(ordersTable.getSelectedRow(), 0).toString();
                System.out.println(orderId);
                //TODO: send orderId to SQL query builder -> get in return a 2D data array
                //A test to see the data is changed upon setDataVector
                if(orderId.equals("555")){
                    itemsTableModel.setDataVector(items1TestData,itemsColumnNames);
                }
                else{
                    itemsTableModel.setDataVector(items2TestData,itemsColumnNames);
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
                    searchParams = buildSearchParameters();
                    //TODO: call method that will use the search params and return the right data
                }
                else{
                    setValidationLabelsVisibility(false);
                    oneRequired.setVisible(true);
                }

            }
        });
    }

    private HashMap buildSearchParameters() {
        HashMap searchParams = new HashMap();
        if(!providersList.getSelectedItem().equals(GUIConstants.SELECT_FIELD))
            searchParams.put(DatabaseConstants.ORDERS_TABLE_PROVIDER_COLUMN, DatabaseController.getProviderIdByName(StringUtils.getStringWithSingleQuotes(providersList.getSelectedItem().toString())));

        if (!categoryList.getSelectedItem().equals(GUIConstants.SELECT_FIELD))
            searchParams.put(DatabaseConstants.PRODUCT_TABLE_ITEM_CATEGORY_COLUMN, StringUtils.getStringWithSingleQuotes(categoryList.getSelectedItem().toString()));

        if(!itemNameTF.getText().equals(GUIConstants.EMPTY_FIELD)) //TODO: fix column names
            searchParams.put(DatabaseConstants.ORDERS_TABLE_ORDER_ID_COLUMN,itemNameTF.getText());

        if(!orderIdTF.getText().equals(GUIConstants.EMPTY_FIELD))
            searchParams.put(DatabaseConstants.ORDERS_TABLE_ORDER_ID_COLUMN,orderIdTF.getText());

        if(deliveryDateChooser.getDate() != null)
            searchParams.put(DatabaseConstants.ORDERS_TABLE_DELIVERY_DATE_COLUMN,deliveryDateChooser.getDate());

        if(closedOrderCB.isSelected())
            searchParams.put(GUIConstants.ORDER_STATUS, OrderStatus.valueOf("delivered"));

        if(openOrderCB.isSelected())
            searchParams.put(GUIConstants.ORDER_STATUS,OrderStatus.valueOf("inProcess"));
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
