package main.java.GUI;

import main.java.common.constants.Constants;

import javax.swing.*;
import java.awt.*;

public class EditItemDialog extends JDialog {
    private JLabel numOfItemsLabel = new JLabel("Items to add? ");
    private JTextField numOfItemsTF = new JTextField();
    private JButton addButton = new JButton("Add");
    private int unitsToAdd;

    public EditItemDialog(JFrame parent){
        super(parent,"Edit item",false);
        setLayout(parent);
    }

    private void setLayout(JFrame parent){
        setLocationRelativeTo(parent);
        setSize(250,120);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        setLayout(new GridBagLayout());
        GridBagConstraints gcMain = new GridBagConstraints();
        gcMain.fill = GridBagConstraints.RELATIVE;

        //First Row//
        gcMain.ipady = 20;
        gcMain.gridy = 0;
        gcMain.weightx = 1;
        gcMain.weighty = 1;

        gcMain.gridx = 0;
        gcMain.anchor = GridBagConstraints.LINE_END;
        add(numOfItemsLabel,gcMain);

        gcMain.gridx = 1;
        gcMain.anchor = GridBagConstraints.LINE_START;
        add(numOfItemsTF,gcMain);

        gcMain.gridx = 2;
        gcMain.anchor = GridBagConstraints.CENTER;
        add(addButton,gcMain);

        //Next Row//
        gcMain.gridy ++;
        gcMain.weightx = 1;
        gcMain.weighty = 1;
        gcMain.gridwidth = 3;
        gcMain.gridx = 0;
        gcMain.anchor = GridBagConstraints.CENTER;
        add(Constants.EXCEED_LIMIT,gcMain);
        Constants.EXCEED_LIMIT.setForeground(Color.red);
        Constants.EXCEED_LIMIT.setVisible(false);

        ///// align fields sizes //////
        Dimension dim = new Dimension(60,7);
        addButton.setPreferredSize(dim);
        numOfItemsTF.setPreferredSize(dim);
    }

    private void setActionListeners(){

    }
}
