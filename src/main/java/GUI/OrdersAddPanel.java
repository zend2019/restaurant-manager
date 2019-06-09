package main.java.GUI;

import main.java.common.StringUtils;
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
import java.util.HashMap;
import java.util.Vector;

import static java.lang.Integer.valueOf;
import static main.java.database.DatabaseController.getAllCategoryNames;
import static main.java.database.DatabaseController.getAllProviderCompanyName;

public class OrdersAddPanel extends IWorkPanel{
    private JLabel providerLabel;
    private JLabel categoryLabel;
    private JLabel itemNameLabel;
    private JLabel unitsLabel;
    private JLabel orderSumLabel;
    private JLabel orderSumFieldLabel;
    private JLabel oneRequired;
    private JLabel noResults;
    private JLabel searchCompleted;
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

    //TEST FIELDS//
    HashMap searchParams = new HashMap();
    private String[] itemsColumnNames = {"Item name","Category","Provider","Available units","Cost per Item","Expected delivery"};
    private String[] orderColumnNames = {"Item name","Category","Provider","Units","Cost per Item","Expected delivery"};
    private String[][] testData ={{"555","Shubi","kabubi","shabubi","2","20.8.15","2"}
                                    ,{"123","Halo","this is dog","kuku","5","20.8.18","2"}};
    private String[] addOrderTest = {"11-22","milky","buku","kuku","5","2020","4"};
    private String orderItem;
    private int orderItemAmount;

    public OrdersAddPanel(){
        initialization();
        setSearchPanelLayout();
        setTableLayout();
        setPlaceOrderLayout();
        setMainLayout();
        setActionListeners();
    }

    @Override
    protected void initialization() {
        providerLabel = new JLabel(GUIConstants.PROVIDER);
        categoryLabel = new JLabel(GUIConstants.CATEGORY);
        itemNameLabel = new JLabel(GUIConstants.ITEM_NAME);
        unitsLabel = new JLabel(GUIConstants.AVAILABLE_AMOUNT);
        orderSumLabel = new JLabel(GUIConstants.ORDER_SUM);
        orderSumFieldLabel = new JLabel(GUIConstants.ZERO);
        oneRequired = new JLabel(GUIConstants.ATLEAST_ONE_FIELD_REQUIRED);
        noResults = new JLabel(GUIConstants.NO_RESULTS);
        searchCompleted = new JLabel(GUIConstants.SEARCH_COMPLETED);
        providersList = new JComboBox();
        categoryList = new JComboBox();
        unitsTF = new JTextField(10);
        itemNameTF = new JTextField(10);
        searchItemButton = new JButton(GUIConstants.SEARCH);
        placeOrderButton = new JButton(GUIConstants.PLACE_ORDER);
        ordersTableModel = new DefaultTableModel(null,orderColumnNames);
        itemsTableModel = new DefaultTableModel(null,itemsColumnNames);
        itemsTable = new JTable(itemsTableModel){public boolean isCellEditable(int row, int column){
            return false;
        }};
        orderTable = new JTable(ordersTableModel){public boolean isCellEditable(int row, int column){
                return false;
            }};
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
        gcMainPanel.gridy ++;
        gcMainPanel.weightx = 0.5;
        gcMainPanel.weighty = 0.1;

        gcMainPanel.gridx = 0;
        gcMainPanel.anchor = GridBagConstraints.LINE_START;
        add(tablesPanel, gcMainPanel);

        gcMainPanel.ipady = 0;
        gcMainPanel.gridy ++;
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
        tablesPanel.add(itemsTablePanel,gcTablesPanel);

        gcTablesPanel.gridx = 0;
        gcTablesPanel.gridy = 1;
        gcTablesPanel.weightx = 1;
        gcTablesPanel.weighty = 1;
        tablesPanel.add(orderTablePanel,gcTablesPanel);
    }

    protected void setItemsTable(){
        Dimension dim = new Dimension(300,200);
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
        itemsTablePanel.add(scrollItemsTable,gcTablePanel);
    }

    protected void setOrderTable(){
        Dimension dim = new Dimension(300,200);
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
        orderTablePanel.add(scrollOrderTable,gcTablePanel);
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
        searchPanel.add(searchItemButton, gcSearchPanel);

        /////// Next row ///////
        gcSearchPanel.gridy ++;

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
        searchPanel.add(oneRequired,gcSearchPanel);

        searchCompleted.setForeground(Color.blue);
        searchCompleted.setVisible(false);
        searchPanel.add(searchCompleted, gcSearchPanel);

        noResults.setForeground(Color.blue);
        noResults.setVisible(false);
        searchPanel.add(noResults, gcSearchPanel);

        alignFieldSizes();
    }

    private void alignFieldSizes() {
        Dimension fieldSize = unitsTF.getPreferredSize();
        providersList.setPreferredSize(fieldSize);
        categoryList.setPreferredSize(fieldSize);
        searchItemButton.setPreferredSize(fieldSize);
    }

    private void setComboBoxes() {
        setCurrentProvider();//TODO: should be adjusted live and not only when running the app first
        setCurrentCategories(); //TODO: same here
        DefaultComboBoxModel providersModel = new DefaultComboBoxModel(providers);
        providersList.setModel(providersModel);
        DefaultComboBoxModel categoryModel = new DefaultComboBoxModel(categories);
        categoryList.setModel(categoryModel);
    }

    private void setCurrentProvider() {
        providers = getAllProviderCompanyName();
        providers.add(0, GUIConstants.SELECT_FIELD);
    }

    private void setCurrentCategories(){
        categories = getAllCategoryNames();
        categories.add(0, GUIConstants.SELECT_FIELD);
    }

    protected void setPlaceOrderLayout(){
        placeOrderPanel.setLayout(new GridBagLayout());
        placeOrderPanel.setBorder(BorderFactory.createSoftBevelBorder(BevelBorder.RAISED));
        GridBagConstraints gcPlaceOrderPanel = new GridBagConstraints();
        gcPlaceOrderPanel.fill = GridBagConstraints.CENTER;
        gcPlaceOrderPanel.insets = new Insets(5,5,5,5);

        /////// First row ///////
        gcPlaceOrderPanel.gridy = 0;
        gcPlaceOrderPanel.weightx = 1;
        gcPlaceOrderPanel.weighty = 0.1;
        gcPlaceOrderPanel.gridx = 0;

        /////// Next row //////
        gcPlaceOrderPanel.gridy ++;
        gcPlaceOrderPanel.weightx = 0.5;
        gcPlaceOrderPanel.weighty = 0.1;
        gcPlaceOrderPanel.gridx = 0;
        gcPlaceOrderPanel.anchor = GridBagConstraints.FIRST_LINE_END;
        placeOrderPanel.add(orderSumLabel, gcPlaceOrderPanel);

        gcPlaceOrderPanel.gridx = 1;
        gcPlaceOrderPanel.anchor = GridBagConstraints.FIRST_LINE_START;
        placeOrderPanel.add(orderSumFieldLabel, gcPlaceOrderPanel);

        gcPlaceOrderPanel.gridx = 2;
        gcPlaceOrderPanel.anchor = GridBagConstraints.FIRST_LINE_START;
        placeOrderPanel.add(placeOrderButton, gcPlaceOrderPanel);

        ///// align fields sizes //////
        Dimension fieldSize = unitsTF.getPreferredSize();
        orderSumFieldLabel.setPreferredSize(fieldSize);
        placeOrderButton.setPreferredSize(fieldSize);
    }

    @Override
    protected void setActionListeners(){
        setSearchButton();
        setPlaceOrderButton();
        setEditUnitsListener();
        setAddItemDialogListener();
        setRemoveItemFromOrderListener();
    }

    private void setEditUnitsListener(){
        itemsTable.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent mouseEvent) {
                JTable table =(JTable) mouseEvent.getSource();
                Point point = mouseEvent.getPoint();
                int row = table.rowAtPoint(point);
                if (mouseEvent.getClickCount() == 2 && table.getSelectedRow() != -1) {
                    orderItem = itemsTable.getValueAt(row, 0).toString();
                    itemDialog.setVisible(true);
                }
            }
        });
    }

    //Used to pass information from Dialog back to the Panel
    private void setAddItemDialogListener(){
        itemDialog.setItemDialogListener(new DialogListener(){
            @Override
            public void setItemInOrder(int units) {
                orderItemAmount = units;
                System.out.println(orderItemAmount);
                //TODO: function that populates the item details based on itemId and updated units into Array or list
                ordersTableModel.addRow(addOrderTest); //insert test data to order table
                orderSum += calculateItemSum(addOrderTest[4],addOrderTest[5]); //update the order sum by teh price (TODO: update the test data)
                setOrderSumFieldLabel(); //updates the sum label
            }
        });
    }

    private void setRemoveItemFromOrderListener(){
        orderTable.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent mouseEvent) {
                JTable table =(JTable) mouseEvent.getSource();
                Point point = mouseEvent.getPoint();
                int row = table.rowAtPoint(point);
                if (mouseEvent.getClickCount() == 2 && table.getSelectedRow() != -1) {
                    orderSum -= calculateItemSum(addOrderTest[4],addOrderTest[5]); //update the order sum by the price (TODO: update the test data)
                    setOrderSumFieldLabel(); //updates the sum label
                    ordersTableModel.removeRow(row);
                }
            }
        });
    }

    //TODO: update according to the data that will be received
    //TODO: consider moving to a utils class
    private Integer calculateItemSum(String numOfItems, String itemPrice){
        Integer numItems = valueOf(numOfItems), price = valueOf(itemPrice);
        return numItems * price;
    }

    private void setOrderSumFieldLabel(){
        orderSumFieldLabel.setText(orderSum.toString());
    }

    private void setPlaceOrderButton() {
        placeOrderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Vector data = ordersTableModel.getDataVector();
                //TODO: how do we create the order ID ??
                //passing the order id to the dialog
                orderPlacedDialog.setPlacedOrderId(123456); //test orderId
                orderPlacedDialog.setVisible(true);
                //TODO: populate the data vector to make according changes in the DB
                //TODO: now the orders table should contain this orders details & order status is in process
            }
        });
    }

    private void setSearchButton() {
        searchItemButton.addActionListener(new ActionListener() {
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
            searchParams.put("product."+DatabaseConstants.PRODUCT_TABLE_ITEM_PROVIDER_COLUMN, DatabaseController.getProviderIdByName(StringUtils.getStringWithSingleQuotes(providersList.getSelectedItem().toString())));

        if (!categoryList.getSelectedItem().equals(GUIConstants.SELECT_FIELD))
            searchParams.put(DatabaseConstants.PRODUCT_TABLE_ITEM_CATEGORY_COLUMN, StringUtils.getStringWithSingleQuotes(categoryList.getSelectedItem().toString()));

        if(!itemNameTF.getText().equals(GUIConstants.EMPTY_FIELD))
            searchParams.put(DatabaseConstants.PRODUCT_TABLE_ITEM_NAME_COLUMN,itemNameTF.getText());

        if(!unitsTF.getText().equals(GUIConstants.EMPTY_FIELD))
            searchParams.put(DatabaseConstants.PRODUCT_TABLE_ITEM_CURRENT_AMOUNT_COLUMN,unitsTF.getText());
        return searchParams;
    }

    private boolean checkAtleastOneNotEmpty(){
        if(     !providersList.getSelectedItem().equals(GUIConstants.SELECT_FIELD) ||
                !categoryList.getSelectedItem().equals(GUIConstants.SELECT_FIELD) ||
                !itemNameTF.getText().equals(GUIConstants.EMPTY_FIELD )||
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
    }
}
