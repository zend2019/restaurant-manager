package main.java.GUI;

import com.toedter.calendar.JDateChooser;
import main.java.BL.Contract.Category;
import main.java.BL.Contract.OrderStatus;
import main.java.common.constants.Constants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class OrdersSearchPanel extends IWorkPanel {
    private JLabel providerLabel;
    private JLabel categoryLabel;
    private JLabel itemNameLabel;
    private JLabel orderIdLabel;
    private JLabel orderStatusLabel;
    private JLabel deliveryDateLabel;
    private JComboBox providersList;
    private JComboBox categoryList;
    private JComboBox itemList;
    private JTextField orderIdTF;
    private JCheckBox openOrderCB;
    private JCheckBox closedOrderCB;
    private JDateChooser deliveryDateChooser;
    private JButton searchOrderButton;
    private JTable ordersTable;
    private JScrollPane scrollTable;
    private JPanel searchPanel;
    private JPanel tablePanel;

    //TEST FIELDS//
    private HashMap searchParams = new HashMap();
    private String[] columnNames = {"Order ID","Units","Cost","Delivery status","Delivery Date"};
    private String[][] testData ={{"555","5","999","Closed","10.05.19"}};
    private String[] providers = {"1","2","3"};
    private String[] items = {"","4","5","6"};

    public OrdersSearchPanel(){
            initialization();
            setSearchPanelLayout();
            setTableLayout();
            setMainLayout();
            setActionListeners();
    }

    @Override
    protected void initialization(){
        providerLabel = new JLabel("Provider: ");
        categoryLabel = new JLabel("Category: ");
        itemNameLabel = new JLabel("Item name: ");
        orderIdLabel = new JLabel("Order ID: ");
        orderStatusLabel = new JLabel("Order status: ");
        deliveryDateLabel = new JLabel("Delivery date: ");
        providersList = new JComboBox();
        categoryList = new JComboBox();
        itemList = new JComboBox();
        openOrderCB = new JCheckBox("In Process");
        closedOrderCB = new JCheckBox("Delivered");
        deliveryDateChooser = new JDateChooser();
        orderIdTF = new JTextField(10);
        searchOrderButton = new JButton("Search order");
        ordersTable = new JTable(testData,columnNames);
        scrollTable = new JScrollPane(ordersTable,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        searchPanel = new JPanel();
        tablePanel = new JPanel();
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
        add(tablePanel, gcMainPanel);
    }

    @Override
    protected void setTableLayout() {
        Dimension dim = new Dimension(300,450);
        scrollTable.setPreferredSize(dim);

        tablePanel.setLayout(new GridBagLayout());
        tablePanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        GridBagConstraints gcTablePanel = new GridBagConstraints();
        gcTablePanel.fill = GridBagConstraints.HORIZONTAL;

        gcTablePanel.gridx = 0;
        gcTablePanel.gridy = 0;
        gcTablePanel.weightx = 1;
        gcTablePanel.weighty = 0.1;

        tablePanel.add(scrollTable,gcTablePanel);
    }

    @Override
    protected void setSearchPanelLayout() {
        /////// Set combo-box ///////
        DefaultComboBoxModel providersModel = new DefaultComboBoxModel(providers);
        providersList.setModel(providersModel);
        DefaultComboBoxModel categoryModel = new DefaultComboBoxModel(Category.values());
        categoryList.setModel(categoryModel);
        DefaultComboBoxModel itemsModel = new DefaultComboBoxModel(items);
        itemList.setModel(itemsModel);

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
        searchPanel.add(itemNameLabel, gcSearchPanel);

        gcSearchPanel.gridx = 1;
        gcSearchPanel.anchor = GridBagConstraints.FIRST_LINE_START;
        searchPanel.add(itemList, gcSearchPanel);

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

        gcSearchPanel.gridx = 3;
        gcSearchPanel.anchor = GridBagConstraints.FIRST_LINE_START;
        searchPanel.add(closedOrderCB, gcSearchPanel);

        gcSearchPanel.gridx = 4;
        gcSearchPanel.anchor = GridBagConstraints.FIRST_LINE_START;

        searchPanel.add(Constants.ATLEAST_ONE_FIELD_REQUIRED,gcSearchPanel);
        Constants.ATLEAST_ONE_FIELD_REQUIRED.setForeground(Color.red);
        Constants.ATLEAST_ONE_FIELD_REQUIRED.setVisible(false);

        ///// align fields sizes //////
        Dimension fieldSize = orderIdTF.getPreferredSize();
        providersList.setPreferredSize(fieldSize);
        categoryList.setPreferredSize(fieldSize);
        itemList.setPreferredSize(fieldSize);
        searchOrderButton.setPreferredSize(fieldSize);
        deliveryDateChooser.setPreferredSize(fieldSize);
    }

    @Override
    protected void setActionListeners(){
        setSearchButton();
    }

    private void setSearchButton() {
        searchOrderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(checkAtleastOneNotEmpty()){
                    setValidationLabelsVisibility(false);
                    searchParams = buildSearchParameters();
                }
                else{
                    setValidationLabelsVisibility(false);
                    Constants.ATLEAST_ONE_FIELD_REQUIRED.setVisible(true);
                }

            }
        });
    }

    private HashMap buildSearchParameters() {
        HashMap searchParams = new HashMap();
        //TODO: Add Providers
        if(!categoryList.getSelectedItem().equals(Category.None))
            searchParams.put(Constants.CATEGORY,categoryList.getSelectedItem());
        if(!itemList.getSelectedItem().equals(Constants.EMPTY_FIELD))
            searchParams.put(Constants.ITEM_NAME,itemList.getSelectedItem());
        if(!orderIdTF.getText().equals(Constants.EMPTY_FIELD))
            searchParams.put(Constants.ORDER_ID,orderIdTF.getText());
        if(deliveryDateChooser.getDate() != null)
            searchParams.put(Constants.DELIVERY_DATE,deliveryDateChooser.getDate());
        if(closedOrderCB.isSelected())
            searchParams.put(Constants.ORDER_STATUS, OrderStatus.valueOf("delivered"));
        if(openOrderCB.isSelected())
            searchParams.put(Constants.ORDER_STATUS,OrderStatus.valueOf("inProcess"));
        return searchParams;
    }

    private boolean checkAtleastOneNotEmpty() {
        //TODO: Provider list
        if(     !categoryList.getSelectedItem().equals(Category.None) ||
                !itemList.getSelectedItem().equals(Constants.EMPTY_FIELD )||
                !orderIdTF.getText().equals(Constants.EMPTY_FIELD )||
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
        Constants.ATLEAST_ONE_FIELD_REQUIRED.setVisible(visibility);
    }

}
