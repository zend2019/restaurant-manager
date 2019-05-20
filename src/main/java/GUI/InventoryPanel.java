package main.java.GUI;

import com.toedter.calendar.JDateChooser;
import main.java.BL.Contract.Category;
import main.java.BL.Contract.Product;
import main.java.common.constants.Constants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import static java.lang.Integer.valueOf;

public class InventoryPanel extends IWorkPanel {
    private Product product;
    private JLabel providerLabel;
    private JLabel categoryLabel;
    private JLabel itemNameLabel;
    private JLabel availableUnitsLabel;
    private JLabel expectedUnitsLabel;
    private JLabel dateLabel;
    private JLabel priceLabel;
    private JComboBox providersList;
    private JComboBox categoryList;
    private JTextField itemNameTF;
    private JTextField availableUnitsTF;
    private JTextField expectedUnitsTF;
    private JTextField priceTF;
    private JDateChooser dateChooser;
    private JButton searchButton;
    private JButton addButton;
    private JTable inventoryTable;
    private JScrollPane scrollTable;
    private JPanel searchPanel;
    private JPanel tablePanel;

    //TEST FIELDS//
    private HashMap searchParams = new HashMap();
    private String[] columnNames = {"ID","Item name","Category","Provider","Available Units","Expected Units","Expiration date"};
    private String[][] testData ={{"555","Shubi","kabubi","shabubi","2","4","20.8.15"}};
    private String[] providers = {"1","2","3"};

    public InventoryPanel(){
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
        itemNameLabel = new JLabel("Item Name: ");
        availableUnitsLabel = new JLabel("Available units: ");
        expectedUnitsLabel = new JLabel("Expected units: ");
        dateLabel = new JLabel("Expiration date: ");
        priceLabel = new JLabel("Price: ");
        providersList = new JComboBox();
        categoryList = new JComboBox();
        itemNameTF = new JTextField(10);
        availableUnitsTF = new JTextField(10);
        expectedUnitsTF = new JTextField(10);
        priceTF = new JTextField(10);
        dateChooser = new JDateChooser();
        searchButton = new JButton("Search");
        addButton = new JButton("Add");
        inventoryTable = new JTable(testData,columnNames);
        scrollTable = new JScrollPane(inventoryTable,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        searchPanel = new JPanel();
        tablePanel = new JPanel();
    }

    @Override
    protected void setMainLayout(){
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
    protected void setTableLayout(){
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
    protected void setSearchPanelLayout(){
        /////// Set combo-box ///////
        DefaultComboBoxModel providersModel = new DefaultComboBoxModel(providers);
        providersList.setModel(providersModel);
        DefaultComboBoxModel categoryModel = new DefaultComboBoxModel(Category.values());
        categoryList.setModel(categoryModel);

        searchPanel.setBorder(BorderFactory.createTitledBorder("Inventory"));
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

        gcSearchPanel.gridx = 4;
        gcSearchPanel.anchor = GridBagConstraints.FIRST_LINE_START;
        searchPanel.add(addButton, gcSearchPanel);

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
        searchPanel.add(priceLabel, gcSearchPanel);

        gcSearchPanel.gridx = 3;
        gcSearchPanel.anchor = GridBagConstraints.FIRST_LINE_START;
        searchPanel.add(priceTF, gcSearchPanel);


        gcSearchPanel.gridx = 4;
        gcSearchPanel.anchor = GridBagConstraints.FIRST_LINE_START;
        searchPanel.add(searchButton, gcSearchPanel);

        /////// Next row ///////
        gcSearchPanel.gridy ++;

        gcSearchPanel.weightx = 0.5;
        gcSearchPanel.weighty = 0.1;

        gcSearchPanel.gridx = 0;
        gcSearchPanel.anchor = GridBagConstraints.FIRST_LINE_END;
        searchPanel.add(availableUnitsLabel, gcSearchPanel);

        gcSearchPanel.gridx = 1;
        gcSearchPanel.anchor = GridBagConstraints.FIRST_LINE_START;
        searchPanel.add(availableUnitsTF, gcSearchPanel);

        gcSearchPanel.gridx = 2;
        gcSearchPanel.anchor = GridBagConstraints.FIRST_LINE_END;
        searchPanel.add(expectedUnitsLabel, gcSearchPanel);

        gcSearchPanel.gridx = 3;
        gcSearchPanel.anchor = GridBagConstraints.FIRST_LINE_START;
        searchPanel.add(expectedUnitsTF, gcSearchPanel);

        /////// Next row ///////
        gcSearchPanel.gridy ++;
        gcSearchPanel.weightx = 0.5;
        gcSearchPanel.weighty = 0.1;

        gcSearchPanel.gridx = 0;
        gcSearchPanel.anchor = GridBagConstraints.FIRST_LINE_END;
        searchPanel.add(dateLabel, gcSearchPanel);

        gcSearchPanel.gridx = 1;
        gcSearchPanel.anchor = GridBagConstraints.FIRST_LINE_START;
        searchPanel.add(dateChooser, gcSearchPanel);

        //Validation labels
        gcSearchPanel.gridx = 3;
        gcSearchPanel.anchor = GridBagConstraints.FIRST_LINE_START;
        searchPanel.add(Constants.ALL_FIELDS_REQUIRED,gcSearchPanel);
        Constants.ALL_FIELDS_REQUIRED.setForeground(Color.red);
        Constants.ALL_FIELDS_REQUIRED.setVisible(false);

        searchPanel.add(Constants.ATLEAST_ONE_FIELD_REQUIRED,gcSearchPanel);
        Constants.ATLEAST_ONE_FIELD_REQUIRED.setForeground(Color.red);
        Constants.ATLEAST_ONE_FIELD_REQUIRED.setVisible(false);

        searchPanel.add(Constants.ITEM_ADDED,gcSearchPanel);
        Constants.ITEM_ADDED.setForeground(Color.green);
        Constants.ITEM_ADDED.setVisible(false);

        searchPanel.add(Constants.SEARCHING,gcSearchPanel);
        Constants.SEARCHING.setForeground(Color.yellow);
        Constants.SEARCHING.setVisible(false);

        ///// align fields sizes //////
        Dimension fieldSize = itemNameTF.getPreferredSize();
        providersList.setPreferredSize(fieldSize);
        categoryList.setPreferredSize(fieldSize);
        addButton.setPreferredSize(fieldSize);
        searchButton.setPreferredSize(fieldSize);
        dateChooser.setPreferredSize(fieldSize);

    }

    @Override
    protected void setActionListeners(){
        setAddButton();
        setSearchButton();
    }

    private void setSearchButton(){
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(checkAtleastOneNotEmpty()){
                    setValidationLabelsVisibility(false);
                    searchParams = buildSearchParameters();
                    Constants.SEARCHING.setVisible(true);
                }
                else{
                    setValidationLabelsVisibility(false);
                    Constants.ATLEAST_ONE_FIELD_REQUIRED.setVisible(true);
                }

            }
        });
    }

    private boolean checkAtleastOneNotEmpty(){
        if(     !categoryList.getSelectedItem().equals(Category.None) ||
                !itemNameTF.getText().equals(Constants.EMPTY_FIELD )||
                !availableUnitsTF.getText().equals(Constants.EMPTY_FIELD )  ||
                !expectedUnitsTF.getText().equals(Constants.EMPTY_FIELD )||
                !priceTF.getText().equals(Constants.EMPTY_FIELD )||
                dateChooser.getDate() != null
        )
            return true;

        else
            return false;
    }


    private HashMap buildSearchParameters(){
        HashMap searchParams = new HashMap();
        //TODO: Add Providers
        if(!categoryList.getSelectedItem().equals(Category.None))
            searchParams.put(Constants.CATEGORY,categoryList.getSelectedItem());
        if(!itemNameTF.getText().equals(Constants.EMPTY_FIELD))
            searchParams.put(Constants.ITEM_NAME,itemNameTF.getText());
        if(!availableUnitsTF.getText().equals(Constants.EMPTY_FIELD))
            searchParams.put(Constants.AVAILABLE_UNITS,availableUnitsTF.getText());
        if(!expectedUnitsTF.getText().equals(Constants.EMPTY_FIELD))
            searchParams.put(Constants.REQUIRED_UNITS,expectedUnitsTF.getText());
        if(!priceTF.getText().equals(Constants.EMPTY_FIELD))
            searchParams.put(Constants.PRICE,priceTF.getText());
        if(dateChooser.getDate() != null){
            searchParams.put(Constants.EXPIRATION_DATE,dateChooser.getDate());
        }
        return searchParams;
    }

    private void setAddButton(){
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!checkNoEmptyFields()){
                    setValidationLabelsVisibility(false);
                    Constants.ALL_FIELDS_REQUIRED.setVisible(true);
                }
                else {
                    setValidationLabelsVisibility(false);
                    product = getProductProperties();
                    Constants.ITEM_ADDED.setVisible(true);
                }
            }
        });
    }

    private boolean checkNoEmptyFields(){
        //TODO: Add Providers: what is the default value?
        if(     categoryList.getSelectedItem() != Category.None &&
                !itemNameTF.getText().equals(Constants.EMPTY_FIELD) &&
                !availableUnitsTF.getText().equals(Constants.EMPTY_FIELD) &&
                !expectedUnitsTF.getText().equals(Constants.EMPTY_FIELD) &&
                !priceTF.getText().equals(Constants.EMPTY_FIELD) &&
                dateChooser.getDate() != null
            )
            return true;
        else
            return false;
    }

    private Product getProductProperties(){
        Product product = new Product();
        //TODO: Item ID ?
        product.setProvider((String)providersList.getSelectedItem());
        product.setCategory((Category)categoryList.getSelectedItem());
        product.setProductName(itemNameTF.getText());
        product.setCurrentProductAmount(valueOf(availableUnitsTF.getText()));
        product.setRequiredAmount(valueOf(expectedUnitsTF.getText()));
        product.setPrice(priceTF.getText());
        product.setExpirationDate(dateChooser.getDate());
        return product;
    }

    @Override
    protected void setValidationLabelsVisibility(boolean visibility){
        Constants.ALL_FIELDS_REQUIRED.setVisible(visibility);
        Constants.ATLEAST_ONE_FIELD_REQUIRED.setVisible(visibility);
        Constants.ITEM_ADDED.setVisible(visibility);
        Constants.SEARCHING.setVisible(visibility);
    }
}
