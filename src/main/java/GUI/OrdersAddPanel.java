package main.java.GUI;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;

public class OrdersAddPanel extends IWorkPanel{
    private JLabel providerLabel;
    private JLabel categoryLabel;
    private JLabel itemNameLabel;
    private JLabel unitsLabel;
    private JLabel orderSumLabel;
    private JLabel orderSumFieldLabel;
    private JComboBox providersList;
    private JComboBox categoryList;
    private JComboBox itemList;
    private JTextField unitsTF;
    private JButton searchItemButton;
    private JButton placeOrderButton;
    private JTable itemsTable;
    private JTable orderTable;
    private JScrollPane scrollItemsTable;
    private JScrollPane scrollOrderTable;
    private JPanel searchPanel;
    private JPanel tablesPanel;
    private JPanel itemsTablePanel;
    private JPanel orderTablePanel;
    private JPanel placeOrderPanel;
    private JSplitPane splitTables;

    private String[] itemsColumnNames = {"SKU","Item name","Category","Provider","Available units","Cost per Item","Expected delivery","Action"};
    private String[] orderColumnNames = {"SKU","Item name","Category","Provider","Units","Cost per Item","Expected delivery","Action"};

    private String[][] testData ={{"555","Shubi","kabubi","shabubi","2","20.8.15","2","Edit\\Add"},{"123","Halo","this is dog","kuku","5","555","2","Edit\\Add"}};

    public OrdersAddPanel(){
        initialization();
        setSearchPanelLayout();
        setTableLayout();
        setPlaceOrderLayout();
        setMainLayout();
    }

    @Override
    protected void initialization() {
        providerLabel = new JLabel("Provider: ");
        categoryLabel = new JLabel("Category: ");
        itemNameLabel = new JLabel("Item name: ");
        unitsLabel = new JLabel("Units: ");
        orderSumLabel = new JLabel("Order Sum: ");
        orderSumFieldLabel = new JLabel("0");
        providersList = new JComboBox();
        categoryList = new JComboBox();
        itemList = new JComboBox();
        unitsTF = new JTextField(10);
        searchItemButton = new JButton("Search Item");
        placeOrderButton = new JButton("Place Order");
        itemsTable = new JTable(testData,itemsColumnNames);
        orderTable = new JTable(testData,orderColumnNames);
        scrollItemsTable = new JScrollPane(itemsTable);
        scrollOrderTable = new JScrollPane(orderTable);
        searchPanel = new JPanel();
        itemsTablePanel = new JPanel();
        orderTablePanel = new JPanel();
        placeOrderPanel = new JPanel();
        tablesPanel = new JPanel();
        //splitTables = new JSplitPane(JSplitPane.VERTICAL_SPLIT,itemsTablePanel,orderTablePanel);
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

        itemsTablePanel.setBorder(BorderFactory.createTitledBorder("Browse items"));

        itemsTablePanel.setLayout(new GridBagLayout());
        //itemsTablePanel.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
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

        orderTablePanel.setBorder(BorderFactory.createTitledBorder("Current order"));

        orderTablePanel.setLayout(new GridBagLayout());
        //orderTablePanel.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
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
        /////// Set combo-box ///////
        DefaultComboBoxModel providersModel = new DefaultComboBoxModel();
        providersModel.addElement("provider 1");
        providersList.setModel(providersModel);
        DefaultComboBoxModel categoryModel = new DefaultComboBoxModel();
        categoryModel.addElement("category 1");
        categoryList.setModel(categoryModel);

        searchPanel.setBorder(BorderFactory.createTitledBorder("Orders"));
        searchPanel.setLayout(new GridBagLayout());
        GridBagConstraints gcSearchPanel = new GridBagConstraints();
        gcSearchPanel.fill = GridBagConstraints.CENTER;
        gcSearchPanel.insets = new Insets(5,5,5,5);

        /////// First row ///////
        gcSearchPanel.gridy = 0;
        gcSearchPanel.weightx = 1;
        gcSearchPanel.weighty = 0.1;
        gcSearchPanel.gridx = 0;

        /////// Next row //////
        gcSearchPanel.gridy ++;
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

        /////// Next row ///////
        gcSearchPanel.gridy ++;

        gcSearchPanel.weightx = 0.5;
        gcSearchPanel.weighty = 0.1;

        gcSearchPanel.gridx = 0;
        gcSearchPanel.anchor = GridBagConstraints.FIRST_LINE_END;
        searchPanel.add(itemNameLabel, gcSearchPanel);

        gcSearchPanel.gridx = 1;
        gcSearchPanel.anchor = GridBagConstraints.FIRST_LINE_START;
        searchPanel.add(itemList, gcSearchPanel);

        gcSearchPanel.gridx = 2;
        gcSearchPanel.anchor = GridBagConstraints.FIRST_LINE_END;
        searchPanel.add(unitsLabel, gcSearchPanel);

        gcSearchPanel.gridx = 3;
        gcSearchPanel.anchor = GridBagConstraints.FIRST_LINE_START;
        searchPanel.add(unitsTF, gcSearchPanel);

        gcSearchPanel.gridx = 4;
        gcSearchPanel.anchor = GridBagConstraints.FIRST_LINE_START;
        searchPanel.add(searchItemButton, gcSearchPanel);

        ///// align fields sizes //////
        Dimension fieldSize = unitsTF.getPreferredSize();
        providersList.setPreferredSize(fieldSize);
        categoryList.setPreferredSize(fieldSize);
        itemList.setPreferredSize(fieldSize);
        searchItemButton.setPreferredSize(fieldSize);
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

    }
}
