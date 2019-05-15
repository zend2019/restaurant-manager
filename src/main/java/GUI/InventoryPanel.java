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

    private String[] columnNames = {"SKU","Item name","Category","Provider","Quantity","Date received"};
    private String[][] testData ={{"555","Shubi","kabubi","shabubi","2","20.8.15"}};

    public InventoryPanel(){
        /////// Initialization ///////
        inventoryPage = new JLabel("Inventory");
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
        //scrollTable.setBounds(50,50,300,100);

        /////// Set combo-box ///////
        DefaultComboBoxModel providersModel = new DefaultComboBoxModel();
        providersModel.addElement("provider 1");
        providersList.setModel(providersModel);
        DefaultComboBoxModel categoryModel = new DefaultComboBoxModel();
        categoryModel.addElement("category 1");
        categoryList.setModel(categoryModel);

        /////// Set layout ///////
        setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();
        gc.fill = GridBagConstraints.NONE;

        /////// First row ///////
        gc.gridy = 0;
        gc.weightx = 1;
        gc.weighty = 0.1;

        gc.gridx = 0;
        gc.anchor = GridBagConstraints.CENTER;
        inventoryPage.setFont(new Font("Tahoma",Font.PLAIN,20));
        add(inventoryPage,gc);

        /////// Next row ///////
        gc.gridy ++;
        gc.weightx = 1;
        gc.weighty = 0.1;

        gc.gridx = 0;
        gc.anchor = GridBagConstraints.FIRST_LINE_END;
        add(providerLabel, gc);

        gc.gridx = 1;
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        add(providersList, gc);

        gc.gridx = 2;
        gc.anchor = GridBagConstraints.FIRST_LINE_END;
        add(categoryLabel, gc);

        gc.gridx = 3;
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        add(categoryList, gc);

        gc.gridx = 4;
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        add(addButton, gc);

        /////// Next row ///////
        gc.gridy ++;

        gc.weightx = 1;
        gc.weighty = 0.1;

        gc.gridx = 0;
        gc.anchor = GridBagConstraints.FIRST_LINE_END;
        add(itemNameLabel, gc);

        gc.gridx = 1;
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        add(itemNameTF, gc);

        gc.gridx = 2;
        gc.anchor = GridBagConstraints.FIRST_LINE_END;
        add(quantityLabel, gc);

        gc.gridx = 3;
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        add(quantityTF, gc);

        gc.gridx = 4;
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        add(searchButton, gc);

        /////// Next row ///////
        gc.gridy ++;

        gc.weightx = 1;
        gc.weighty = 0.1;
        gc.gridx = 0;
        gc.gridwidth = 30;
        gc.gridheight = 3;
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        add(scrollTable,gc);

        /////// Next row ///////
        gc.gridy ++;

    }
}
