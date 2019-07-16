package main.java.GUI;

import com.toedter.calendar.JDateChooser;
import main.java.BL.Contract.Category;
import main.java.BL.Contract.Logic.IProductManager;
import main.java.BL.Contract.Logic.IProviderManaging;
import main.java.BL.Contract.Logic.ProductController;
import main.java.BL.Contract.Logic.ProviderController;
import main.java.BL.Contract.Product;
import main.java.GUI.commonUI.Common;
import main.java.common.DateUtils;
import main.java.common.StringUtils;
import main.java.common.constants.Constants;
import main.java.common.constants.DatabaseConstants;
import main.java.common.constants.GUIConstants;
import main.java.database.DatabaseController;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Vector;

import static java.lang.Integer.valueOf;
import static main.java.database.DatabaseController.getAllCategoryNames;
import static main.java.database.DatabaseController.getAllProviderCompanyName;

public class InventoryPanel extends IWorkPanel {
    private Product product;
    private JLabel providerLabel;
    private JLabel categoryLabel;
    private JLabel itemNameLabel;
    private JLabel availableAmountLabel;
    private JLabel currentAmountLabel;
    private JLabel dateLabel;
    private JLabel priceLabel;
    private JLabel allRequired;
    private JLabel oneRequired;
    private JLabel itemAdded;
    private JLabel noResults;
    private JLabel searchCompleted;
    private JComboBox providersList;
    private JComboBox categoryList;
    private JTextField itemNameTF;
    private JTextField currentAmountTF;
    private JTextField availableAmountTF;
    private JTextField priceTF;
    private JDateChooser dateChooser;
    private JButton searchButton;
    private JButton addButton;
    private JTable inventoryTable;
    private DefaultTableModel model;
    private JScrollPane scrollTable;
    private JPanel searchPanel;
    private JPanel tablePanel;
    private HashMap searchParams = new HashMap();
    private String[] inventoryColumnNames = {"ID", "Item name", "Category", "Provider", "Current amount", "Required amount", "Expiration date"};
    private Vector<String> providers;
    private Vector<String> categories;
    private IProviderManaging providerManaging;
    private IProductManager productManager;

    public InventoryPanel() {
        initialization();
        setSearchPanelLayout();
        setTableLayout();
        setMainLayout();
        setActionListeners();
        providerManaging = new ProviderController();
        productManager = new ProductController();
    }

    @Override
    protected void initialization() {
        allRequired = new JLabel(GUIConstants.ALL_FIELDS_REQUIRED);
        oneRequired = new JLabel(GUIConstants.ATLEAST_ONE_FIELD_REQUIRED);
        itemAdded = new JLabel(GUIConstants.ITEM_ADDED);
        noResults = new JLabel(GUIConstants.NO_RESULTS);
        searchCompleted = new JLabel(GUIConstants.SEARCH_COMPLETED);
        providerLabel = new JLabel(GUIConstants.PROVIDER);
        categoryLabel = new JLabel(GUIConstants.CATEGORY);
        itemNameLabel = new JLabel(GUIConstants.ITEM_NAME);
        availableAmountLabel = new JLabel(GUIConstants.CURRENT_AMOUNT);
        currentAmountLabel = new JLabel(GUIConstants.REQUIRED_AMOUNT);
        dateLabel = new JLabel(GUIConstants.EXPIRATION_DATE);
        priceLabel = new JLabel(GUIConstants.PRICE);
        providersList = new JComboBox();
        categoryList = new JComboBox();
        itemNameTF = new JTextField(10);
        currentAmountTF = new JTextField(10);
        availableAmountTF = new JTextField(10);
        priceTF = new JTextField(10);
        dateChooser = new JDateChooser();
        searchButton = new JButton(GUIConstants.SEARCH);
        addButton = new JButton(GUIConstants.ADD_PRODUCT);
        model = new DefaultTableModel(null, inventoryColumnNames);
        inventoryTable = new JTable(model) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
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

        setComboBoxes();

        searchPanel.setBorder(BorderFactory.createTitledBorder("Restaurant Inventory"));
        searchPanel.setLayout(new GridBagLayout());
        GridBagConstraints gcSearchPanel = new GridBagConstraints();
        gcSearchPanel.fill = GridBagConstraints.CENTER;
        gcSearchPanel.insets = new Insets(5, 5, 5, 5);

        /////// First row ///////
        Common.FirstRow(gcSearchPanel);

        /////// Next row //////
        Common.NextRow(gcSearchPanel,0.5,0.1,0);

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
        searchPanel.add(availableAmountLabel, gcSearchPanel);

        gcSearchPanel.gridx = 1;
        gcSearchPanel.anchor = GridBagConstraints.FIRST_LINE_START;
        searchPanel.add(currentAmountTF, gcSearchPanel);

        gcSearchPanel.gridx = 2;
        gcSearchPanel.anchor = GridBagConstraints.FIRST_LINE_END;
        searchPanel.add(currentAmountLabel, gcSearchPanel);

        gcSearchPanel.gridx = 3;
        gcSearchPanel.anchor = GridBagConstraints.FIRST_LINE_START;
        searchPanel.add(availableAmountTF, gcSearchPanel);

        /////// Next row ///////
        Common.NextRow(gcSearchPanel,0.5,0.1,0);

        gcSearchPanel.anchor = GridBagConstraints.FIRST_LINE_END;
        searchPanel.add(dateLabel, gcSearchPanel);

        gcSearchPanel.gridx = 1;
        gcSearchPanel.anchor = GridBagConstraints.FIRST_LINE_START;
        searchPanel.add(dateChooser, gcSearchPanel);

        //Validation labels
        gcSearchPanel.gridx = 3;
        gcSearchPanel.anchor = GridBagConstraints.FIRST_LINE_START;

        allRequired.setForeground(Color.red);
        allRequired.setVisible(false);
        searchPanel.add(allRequired, gcSearchPanel);

        searchPanel.add(oneRequired, gcSearchPanel);
        oneRequired.setForeground(Color.red);
        oneRequired.setVisible(false);

        itemAdded.setForeground(Color.blue);
        itemAdded.setVisible(false);
        searchPanel.add(itemAdded, gcSearchPanel);

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
        Dimension fieldSize = itemNameTF.getPreferredSize();
        providersList.setPreferredSize(fieldSize);
        categoryList.setPreferredSize(fieldSize);
        dateChooser.setPreferredSize(fieldSize);

        Dimension buttonSize = addButton.getPreferredSize();
        searchButton.setPreferredSize(buttonSize);
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
                    Vector<Product> x = productManager.getListOfProducts(buildSearchProductParameters());
                    if (x.size() == 0) {
                        model.setDataVector(convertProductVectorToInventoryMatrix(x), inventoryColumnNames);
                        noResults.setVisible(true);
                    } else {
                        setValidationLabelsVisibility(false);
                        model.setDataVector(convertProductVectorToInventoryMatrix(x), inventoryColumnNames);
                        searchCompleted.setVisible(true);
                    }
                } else {
                    setValidationLabelsVisibility(false);
                    oneRequired.setVisible(true);
                }

            }
        });
    }

    private void setAddButton() {
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!checkNoEmptyFields()) {
                    setValidationLabelsVisibility(false);
                    allRequired.setVisible(true);
                } else {
                    setValidationLabelsVisibility(false);
                    Product product = new Product();
                    product = getProductProperties();
                    product.setProductId(productManager.addProduct(product));
                    itemAdded.setVisible(true);
                }
            }
        });
    }

    //{"ID", "Item name", "Category", "Provider", "Current amount", "Required amount", "Expiration date"};
    private String[][] convertProductVectorToInventoryMatrix(Vector<Product> productVector) {
        String[][] matrix = new String[productVector.size()][Constants.INVENTORY_MATRIX_COLUMNS];
        for (int i = 0; i < productVector.size(); i++) {
            String[] array = {
                    productVector.get(i).getProductId(),
                    productVector.get(i).getProductName(),
                    String.valueOf(productVector.get(i).getCategory()),
                    providerManaging.getProviderNameById(productVector.get(i).getProviderId()),
                    String.valueOf(productVector.get(i).getCurrentProductAmount()),
                    String.valueOf(productVector.get(i).getRequiredAmount()),
                    DateUtils.formatDateToString(productVector.get(i).getExpirationDate())
            };
            matrix[i] = array;
        }

        return matrix;
    }

    private boolean checkAtleastOneNotEmpty() {
        if (!providersList.getSelectedItem().equals(GUIConstants.SELECT_FIELD) ||
                !categoryList.getSelectedItem().equals(GUIConstants.SELECT_FIELD) ||
                !itemNameTF.getText().equals(GUIConstants.EMPTY_FIELD) ||
                !currentAmountTF.getText().equals(GUIConstants.EMPTY_FIELD) ||
                !availableAmountTF.getText().equals(GUIConstants.EMPTY_FIELD) ||
                !priceTF.getText().equals(GUIConstants.EMPTY_FIELD) ||
                dateChooser.getDate() != null
        )
            return true;

        else
            return false;
    }


    private HashMap buildSearchProductParameters() {
        HashMap searchParams = new HashMap();
        if (!providersList.getSelectedItem().equals(GUIConstants.SELECT_FIELD))
            searchParams.put(DatabaseConstants.PRODUCT_TABLE_ITEM_PROVIDER_COLUMN, providerManaging.getProviderIdByName(StringUtils.getStringWithSingleQuotes(providersList.getSelectedItem().toString())));

        if (!categoryList.getSelectedItem().equals(GUIConstants.SELECT_FIELD))
            searchParams.put(DatabaseConstants.PRODUCT_TABLE_ITEM_CATEGORY_COLUMN, StringUtils.getStringWithSingleQuotes(categoryList.getSelectedItem().toString()));

        if (!itemNameTF.getText().equals(GUIConstants.EMPTY_FIELD))
            searchParams.put(DatabaseConstants.PRODUCT_TABLE_ITEM_NAME_COLUMN, StringUtils.getStringWithSingleQuotes(itemNameTF.getText()));

        if (!currentAmountTF.getText().equals(GUIConstants.EMPTY_FIELD))
            searchParams.put(DatabaseConstants.PRODUCT_TABLE_ITEM_CURRENT_AMOUNT_COLUMN, StringUtils.getStringWithSingleQuotes(currentAmountTF.getText()));

        if (!availableAmountTF.getText().equals(GUIConstants.EMPTY_FIELD))
            searchParams.put(DatabaseConstants.PRODUCT_TABLE_ITEM_REQUIRED_AMOUNT_COLUMN, StringUtils.getStringWithSingleQuotes(availableAmountTF.getText()));

        if (!priceTF.getText().equals(GUIConstants.EMPTY_FIELD))
            searchParams.put(DatabaseConstants.PRODUCT_TABLE_ITEM_PRICE_COLUMN, StringUtils.getStringWithSingleQuotes(priceTF.getText()));

        if (dateChooser.getDate() != null) {
            searchParams.put(DatabaseConstants.PRODUCT_TABLE_ITEM_EXPIRATION_DATE_COLUMN, StringUtils.getStringWithSingleQuotes(DateUtils.formatDateToString(dateChooser.getDate())));
        }
        return searchParams;
    }


    private boolean checkNoEmptyFields() {
        if (!providersList.getSelectedItem().equals(GUIConstants.SELECT_FIELD) &&
                !categoryList.getSelectedItem().equals(GUIConstants.SELECT_FIELD) &&
                !itemNameTF.getText().equals(GUIConstants.EMPTY_FIELD) &&
                !currentAmountTF.getText().equals(GUIConstants.EMPTY_FIELD) &&
                !availableAmountTF.getText().equals(GUIConstants.EMPTY_FIELD) &&
                !priceTF.getText().equals(GUIConstants.EMPTY_FIELD) &&
                dateChooser.getDate() != null
        )
            return true;
        else
            return false;
    }

    private Product getProductProperties() {
        Product product = new Product();
        product.setProviderId(providerManaging.getProviderIdByName(StringUtils.getStringWithSingleQuotes(providersList.getSelectedItem().toString())));
        product.setCategory(Category.valueOf(categoryList.getSelectedItem().toString()));
        product.setProductName(itemNameTF.getText());
        product.setCurrentProductAmount(valueOf(currentAmountTF.getText()));
        product.setRequiredAmount(valueOf(availableAmountTF.getText()));
        product.setPrice(priceTF.getText());
        product.setExpirationDate(dateChooser.getDate());
        return product;
    }

    @Override
    protected void setValidationLabelsVisibility(boolean visibility) {
        allRequired.setVisible(visibility);
        oneRequired.setVisible(visibility);
        itemAdded.setVisible(visibility);
        searchCompleted.setVisible(visibility);
        noResults.setVisible(visibility);
    }
}
