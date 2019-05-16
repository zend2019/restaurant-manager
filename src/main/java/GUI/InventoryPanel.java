package main.java.GUI;

import javax.swing.*;
import java.awt.*;

public class InventoryPanel extends JPanel {
    private JLabel inventoryPage;
    private JLabel providerLabel;
    private JLabel categoryLabel;
    private JLabel itemNameLabel;
    private JLabel quantityLabel;
    private JComboBox providersList;
    private JComboBox categoryList;
    private JTextField itemNameTF;
    private JTextField quantityTF;
    private JButton searchButton;
    private JButton addButton;
    private JTable inventoryTable;
    private JScrollPane scrollTable;
    private JPanel searchPanel = new JPanel();
    private JPanel tablePanel = new JPanel();

    private String[] columnNames = {"SKU","Item name","Category","Provider","Quantity","Date received"};
    private String[][] testData ={{"555","Shubi","kabubi","shabubi","2","20.8.15"},{"123","Halo","this is dog","kuku","5","555"}};

    public InventoryPanel(){
        initialization();

        /////// Set main layout ///////
        setLayout(new GridBagLayout());
        GridBagConstraints gcMainPanel = new GridBagConstraints();
        gcMainPanel.fill = GridBagConstraints.HORIZONTAL;

        setSearchPanelLayout();
        setTableLayout();

        gcMainPanel.ipady = 50;
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
        gcMainPanel.anchor = GridBagConstraints.CENTER;
        add(tablePanel, gcMainPanel);


    }

    private void initialization(){
        //inventoryPage = new JLabel("Inventory");
        providerLabel = new JLabel("Provider: ");
        categoryLabel = new JLabel("Category: ");
        itemNameLabel = new JLabel("Item Name: ");
        quantityLabel = new JLabel("Quantity: ");
        providersList = new JComboBox();
        categoryList = new JComboBox();
        itemNameTF = new JTextField(10);
        quantityTF = new JTextField(10);
        searchButton = new JButton("Search");
        addButton = new JButton("Add");
        inventoryTable = new JTable(testData,columnNames);
        scrollTable = new JScrollPane(inventoryTable,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    }

    private void setTableLayout(){
        tablePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        tablePanel.setLayout(new GridBagLayout());
        GridBagConstraints gcTablePanel = new GridBagConstraints();
        gcTablePanel.fill = GridBagConstraints.HORIZONTAL;

        gcTablePanel.gridx = 0;
        gcTablePanel.gridy = 0;
        gcTablePanel.weightx = 1;
        gcTablePanel.weighty = 0.1;

        tablePanel.add(scrollTable,gcTablePanel);
    }

    private void setSearchPanelLayout(){
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

        /////// First row ///////
        gcSearchPanel.insets = new Insets(5,5,5,5);
        gcSearchPanel.gridy = 0;
        gcSearchPanel.weightx = 1;
        gcSearchPanel.weighty = 0.1;

        gcSearchPanel.gridx = 0;
        //gc.anchor = GridBagConstraints.CENTER;
        //inventoryPage.setFont(new Font("Tahoma",Font.PLAIN,20));
        //searchPanel.add(inventoryPage,gc);

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
        searchPanel.add(quantityLabel, gcSearchPanel);

        gcSearchPanel.gridx = 3;
        gcSearchPanel.anchor = GridBagConstraints.FIRST_LINE_START;
        searchPanel.add(quantityTF, gcSearchPanel);

        gcSearchPanel.gridx = 4;
        gcSearchPanel.anchor = GridBagConstraints.FIRST_LINE_START;
        searchPanel.add(searchButton, gcSearchPanel);
    }
}
