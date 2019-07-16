package main.java.GUI;

import main.java.BL.Contract.Logic.IProductManager;
import main.java.BL.Contract.Logic.IProviderManaging;
import main.java.BL.Contract.Logic.ProductController;
import main.java.BL.Contract.Logic.ProviderController;
import main.java.BL.Contract.Product;
import main.java.GUI.commonUI.Common;
import main.java.common.constants.Constants;
import main.java.common.constants.GUIConstants;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

public class OutOfStockReport extends IBaseWorkPanel {

    private JPanel searchPanel;
    private JPanel tablePanel;
    private JScrollPane scrollTable;
    private JTable productTable;
    private DefaultTableModel model;
    private JLabel allRequired;
    private JLabel oneRequired;
    private JLabel noResults;
    private JLabel searchCompleted;
    private JButton searchButton;
    private JTextField itemNameTF;
    private String[] inventoryColumnNames = {"ID", "Item name", "Category", "Provider", "Amount To Order"};
    private IProviderManaging providerManaging;
    private IProductManager productManager;

    public OutOfStockReport() {
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
        searchPanel = new JPanel();
        tablePanel = new JPanel();
        model = new DefaultTableModel(null, inventoryColumnNames);
        productTable = new JTable(model) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        scrollTable = new JScrollPane(productTable, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        allRequired = new JLabel(GUIConstants.ALL_FIELDS_REQUIRED);
        oneRequired = new JLabel(GUIConstants.ATLEAST_ONE_FIELD_REQUIRED);
        noResults = new JLabel(GUIConstants.NO_RESULTS);
        searchCompleted = new JLabel(GUIConstants.SEARCH_COMPLETED);
        searchButton = new JButton(GUIConstants.OUT_OF_STOCK_REPORT);
        itemNameTF = new JTextField(10);
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
        Common.NextRow(gcMainPanel, 0.5, 0.1, 0);
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
        searchPanel.setBorder(BorderFactory.createTitledBorder("Daily Report"));
        searchPanel.setLayout(new GridBagLayout());
        GridBagConstraints gcSearchPanel = new GridBagConstraints();
        gcSearchPanel.fill = GridBagConstraints.CENTER;
        gcSearchPanel.insets = new Insets(5, 5, 5, 5);

        /////// First row ///////
        Common.FirstRow(gcSearchPanel);
        /////// Next row //////
        Common.NextRow(gcSearchPanel, 0.5, 0.1, 0);
        gcSearchPanel.anchor = GridBagConstraints.FIRST_LINE_START;
        searchPanel.add(searchButton, gcSearchPanel);

        //Validation labels
        gcSearchPanel.gridx = 1;
        gcSearchPanel.anchor = GridBagConstraints.FIRST_LINE_START;

        allRequired.setForeground(Color.red);
        allRequired.setVisible(false);
        searchPanel.add(allRequired, gcSearchPanel);

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

    @Override
    protected void alignFieldSizes() {
        Dimension fieldSize = itemNameTF.getPreferredSize();
        //searchButton.setPreferredSize(fieldSize);
    }

    @Override
    protected void setActionListeners() {
        setSearchButton();
    }

    private String[][] convertProductVectorToInventoryMatrix(Vector<Product> productVector) {
        String[][] matrix = new String[productVector.size()][Constants.REPORT_MATRIX_COLUMNS];
        for (int i = 0; i < productVector.size(); i++) {
            String[] array = {
                    productVector.get(i).getProductId(),
                    productVector.get(i).getProductName(),
                    String.valueOf(productVector.get(i).getCategory()),
                    providerManaging.getProviderNameById(productVector.get(i).getProviderId()),
                    String.valueOf(productVector.get(i).getAmountOfLake()),
            };
            matrix[i] = array;
        }
        return matrix;
    }


    private void setSearchButton() {
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Vector<Product> x = productManager.getListOfAllProducts();
                if (x.size() == 0) {
                    model.setDataVector(convertProductVectorToInventoryMatrix(x), inventoryColumnNames);
                    noResults.setVisible(true);
                } else {
                    model.setDataVector(convertProductVectorToInventoryMatrix(x), inventoryColumnNames);
                    searchCompleted.setVisible(true);
                }
            }
        });
    }
}
