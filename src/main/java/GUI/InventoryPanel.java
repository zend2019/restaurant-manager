package main.java.GUI;

import com.toedter.calendar.JDateChooser;
import main.java.BL.Contract.Category;
import main.java.BL.Contract.Product;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

    private String[] columnNames = {"ID","Item name","Category","Provider","Available Units","Expected Units","Expiration date"};
    private String[][] testData ={{"555","Shubi","kabubi","shabubi","2","4","20.8.15"}};

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
        DefaultComboBoxModel providersModel = new DefaultComboBoxModel();
        providersModel.addElement("provider 1");
        providersList.setModel(providersModel);
        DefaultComboBoxModel categoryModel = new DefaultComboBoxModel();
        categoryModel.addElement("category 1");
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
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO: add empty field validations
                //product = getProductProperties();
            }
        });

    }

    protected Product getProductProperties(){
        Product product = new Product();
        //set item ID
        product.setProvider((String) providersList.getSelectedItem());
        //product.setCategory((Category) categoryList.getSelectedItem());
        product.setProductName(itemNameTF.getText());
        product.setCurrentProductAmount(valueOf(availableUnitsTF.getText()));
        product.setPrice(priceTF.getText());
        product.setExpirationDate(dateChooser.getDate());
        return product;
    }

   // protected Product
}
