package main.java.GUI;

import com.toedter.calendar.JDateChooser;
import main.java.BL.Contract.Category;
import main.java.BL.Contract.Product;
import main.java.common.DateUtils;
import main.java.common.StringUtils;
import main.java.common.constants.Constants;
import main.java.common.constants.DatabaseConstants;
import main.java.database.DatabaseController;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Vector;

import static java.lang.Integer.valueOf;
import static main.java.database.DatabaseController.getAllProviderCompanyName;

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
    private DefaultTableModel model;
    private JScrollPane scrollTable;
    private JPanel searchPanel;
    private JPanel tablePanel;

    //TEST FIELDS//
    private HashMap searchParams = new HashMap();
    private String[] columnNames = {"ID", "Item name", "Category", "Provider", "Available Units", "Expected Units", "Expiration date"};
    private String[][] testData = {{"555", "Shubi", "kabubi", "shabubi", "2", "4", "20.08.15"}};
    private Vector<String> providers;
    private String[][] searchTestData = {{"1124", "Shubi", "bubi", "shabubi", "2", "4", "05.06.75"},
            {"4454", "halo", "this", "is dog", "5", "66", "21.09.16"}};

    public InventoryPanel() {
        initialization();
        setSearchPanelLayout();
        setTableLayout();
        setMainLayout();
        setActionListeners();
    }

    @Override
    protected void initialization() {
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
        model = new DefaultTableModel(testData, columnNames);
        inventoryTable = new JTable(model);
        scrollTable = new JScrollPane(inventoryTable, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
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
        gcMainPanel.gridy++;
        gcMainPanel.weightx = 0.5;
        gcMainPanel.weighty = 0.1;

        gcMainPanel.gridx = 0;
        gcMainPanel.anchor = GridBagConstraints.LINE_START;
        add(tablePanel, gcMainPanel);
    }

    @Override
    protected void setTableLayout() {
        Dimension dim = new Dimension(300, 450);
        scrollTable.setPreferredSize(dim);

        tablePanel.setLayout(new GridBagLayout());
        tablePanel.setBorder(BorderFactory.createTitledBorder("Browse items"));
        GridBagConstraints gcTablePanel = new GridBagConstraints();
        gcTablePanel.fill = GridBagConstraints.HORIZONTAL;

        gcTablePanel.gridx = 0;
        gcTablePanel.gridy = 0;
        gcTablePanel.weightx = 1;
        gcTablePanel.weighty = 0.1;
        gcTablePanel.anchor = GridBagConstraints.FIRST_LINE_START;
        tablePanel.add(scrollTable, gcTablePanel);
    }

    @Override
    protected void setSearchPanelLayout() {
        /////// Set combo-box ///////
        setCurrentProvider();//TODO: @ELINA should be adjusted live and not only when running the app first
        DefaultComboBoxModel providersModel = new DefaultComboBoxModel(providers);
        providersList.setModel(providersModel);
        DefaultComboBoxModel categoryModel = new DefaultComboBoxModel(Category.values());
        categoryList.setModel(categoryModel);

        searchPanel.setBorder(BorderFactory.createTitledBorder("Inventory"));
        searchPanel.setLayout(new GridBagLayout());
        GridBagConstraints gcSearchPanel = new GridBagConstraints();
        gcSearchPanel.fill = GridBagConstraints.CENTER;
        gcSearchPanel.insets = new Insets(5, 5, 5, 5);

        /////// First row ///////
        gcSearchPanel.gridy = 0;
        gcSearchPanel.weightx = 1;
        gcSearchPanel.weighty = 0.1;
        gcSearchPanel.gridx = 0;

        /////// Next row //////
        gcSearchPanel.gridy++;
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
        searchPanel.add(priceLabel, gcSearchPanel);

        gcSearchPanel.gridx = 3;
        gcSearchPanel.anchor = GridBagConstraints.FIRST_LINE_START;
        searchPanel.add(priceTF, gcSearchPanel);


        gcSearchPanel.gridx = 4;
        gcSearchPanel.anchor = GridBagConstraints.FIRST_LINE_START;
        searchPanel.add(searchButton, gcSearchPanel);

        /////// Next row ///////
        gcSearchPanel.gridy++;

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
        gcSearchPanel.gridy++;
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
        searchPanel.add(Constants.ALL_FIELDS_REQUIRED, gcSearchPanel);
        Constants.ALL_FIELDS_REQUIRED.setForeground(Color.red);
        Constants.ALL_FIELDS_REQUIRED.setVisible(false);

        Constants.ATLEAST_ONE_FIELD_REQUIRED.setForeground(Color.red);
        Constants.ATLEAST_ONE_FIELD_REQUIRED.setVisible(false);
        searchPanel.add(Constants.ATLEAST_ONE_FIELD_REQUIRED, gcSearchPanel);


        searchPanel.add(Constants.ITEM_ADDED, gcSearchPanel);
        Constants.ITEM_ADDED.setForeground(Color.green);
        Constants.ITEM_ADDED.setVisible(false);

        searchPanel.add(Constants.SEARCHING, gcSearchPanel);
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

    private void setCurrentProvider() {
        providers = getAllProviderCompanyName();
        providers.add(0, ""); //TODO: @ELINA fix in the hashmap isEmpty or something like..
    }

    @Override
    protected void setActionListeners() {
        setAddButton();
        setSearchButton();
    }

    private void setSearchButton() {
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (checkAtleastOneNotEmpty()) {
                    setValidationLabelsVisibility(false);
                    searchParams = buildSearchProductParameters();
                    Vector<Product> x = DatabaseController.getListOfProviders(searchParams);
                    //TODO: pass the search parameters to sql query builder, return the list of items
                    Constants.SEARCHING.setVisible(true);
                    model.setDataVector(convertProductVectorToInventoryMatrix(x), columnNames); //Set the table with test data
                } else {
                    setValidationLabelsVisibility(false);
                    Constants.ATLEAST_ONE_FIELD_REQUIRED.setVisible(true);
                    //Constants.ALL_FIELDS_REQUIRED.setVisible(true);
                }

            }
        });
    }

    //{"ID", "Item name", "Category", "Provider", "Available Units", "Expected Units", "Expiration date"}
    private String[][] convertProductVectorToInventoryMatrix(Vector<Product> productVector) {
        String[][] matrix = new String[productVector.size()][Constants.INVENTORY_MATRIX_COLUMNS];
        for (int i = 0; i < productVector.size(); i++) {
            String[] array = {
                    productVector.get(i).getProductId(),
                    productVector.get(i).getProductName(),
                    String.valueOf(productVector.get(i).getCategory()),
                    productVector.get(i).getProviderId(),
                    String.valueOf(productVector.get(i).getCurrentProductAmount()),
                    String.valueOf(productVector.get(i).getRequiredAmount()),
                    DateUtils.formatDateToString(productVector.get(i).getExpirationDate())
            };
            matrix[i] = array;
        }
        return matrix;
    }

    private boolean checkAtleastOneNotEmpty() {
        if (!categoryList.getSelectedItem().equals(Category.None) ||
                !itemNameTF.getText().equals(Constants.EMPTY_FIELD) ||
                !availableUnitsTF.getText().equals(Constants.EMPTY_FIELD) ||
                !expectedUnitsTF.getText().equals(Constants.EMPTY_FIELD) ||
                !priceTF.getText().equals(Constants.EMPTY_FIELD) ||
                dateChooser.getDate() != null
                )
            return true;

        else
            return false;
    }


    private HashMap buildSearchProductParameters() {
        HashMap searchParams = new HashMap();
        //TODO: Add Providers
        //TODO: @ELINA add single quotes to hashmap value ('<X>' and not <X>)
        if (!categoryList.getSelectedItem().equals(Category.None))
            searchParams.put(Constants.CATEGORY, categoryList.getSelectedItem());
        if (!itemNameTF.getText().equals(Constants.EMPTY_FIELD))
            searchParams.put(DatabaseConstants.PRODUCT_TABLE_ITEM_NAME_COLUMN, StringUtils.getStringWithSingleQuotes(itemNameTF.getText()));
        if (!availableUnitsTF.getText().equals(Constants.EMPTY_FIELD))
            searchParams.put(Constants.AVAILABLE_UNITS, StringUtils.getStringWithSingleQuotes(availableUnitsTF.getText()));
        if (!expectedUnitsTF.getText().equals(Constants.EMPTY_FIELD))
            searchParams.put(Constants.REQUIRED_UNITS, StringUtils.getStringWithSingleQuotes(expectedUnitsTF.getText()));
        if (!priceTF.getText().equals(Constants.EMPTY_FIELD))
            searchParams.put(Constants.PRICE, StringUtils.getStringWithSingleQuotes(priceTF.getText()));
        if (dateChooser.getDate() != null) {
            searchParams.put(Constants.EXPIRATION_DATE, DateUtils.formatDateToString(dateChooser.getDate()));
        }
        return searchParams;
    }

    private void setAddButton() {
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!checkNoEmptyFields()) {
                    setValidationLabelsVisibility(false);
                    Constants.ALL_FIELDS_REQUIRED.setVisible(true);
                } else {
                    setValidationLabelsVisibility(false);
                    product = getProductProperties();
                    Constants.ITEM_ADDED.setVisible(true);
                }
            }
        });
    }

    private boolean checkNoEmptyFields() {
        //TODO: Add Providers: what is the default value?
        if (categoryList.getSelectedItem() != Category.None &&
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

    private Product getProductProperties() {
        Product product = new Product();
        //TODO: Item ID ?
//        product.setProvider((String)providersList.getSelectedItem());
        product.setCategory((Category) categoryList.getSelectedItem());
        product.setProductName(itemNameTF.getText());
        product.setCurrentProductAmount(valueOf(availableUnitsTF.getText()));
        product.setRequiredAmount(valueOf(expectedUnitsTF.getText()));
        product.setPrice(priceTF.getText());
        product.setExpirationDate(dateChooser.getDate());
        return product;
    }

    @Override
    protected void setValidationLabelsVisibility(boolean visibility) {
        Constants.ALL_FIELDS_REQUIRED.setVisible(visibility);
        Constants.ATLEAST_ONE_FIELD_REQUIRED.setVisible(visibility);
        Constants.ITEM_ADDED.setVisible(visibility);
        Constants.SEARCHING.setVisible(visibility);
    }
}
