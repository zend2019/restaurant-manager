package main.java.GUI;

import javax.swing.*;
import java.awt.*;

public class EditItemDialog extends JDialog {
    private JLabel numOfItemsLabel = new JLabel("Items to add? ");
    private JTextField numOfItemsTF = new JTextField(10);
    private JButton addButton = new JButton("Add");

    public EditItemDialog(JFrame parent){
        super(parent,"Edit item",false);
        setLayout();
    }

    private void setLayout(){
        setSize(250,100);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new GridBagLayout());
        GridBagConstraints gcMain = new GridBagConstraints();
        gcMain.fill = GridBagConstraints.VERTICAL;

        //First Row//
        gcMain.ipady = 10;
        gcMain.gridy = 0;
        gcMain.weightx = 0.5;
        gcMain.weighty = 0.1;

        gcMain.gridx = 0;
        gcMain.anchor = GridBagConstraints.LINE_END;
        add(numOfItemsLabel,gcMain);

        gcMain.gridx = 1;
        gcMain.anchor = GridBagConstraints.LINE_START;
        add(numOfItemsTF,gcMain);

        gcMain.gridx = 2;
        gcMain.anchor = GridBagConstraints.CENTER;
        add(addButton,gcMain);

        ///// align fields sizes //////
        Dimension dim = new Dimension(70,70);
        addButton.setPreferredSize(dim);
    }

    private void setActionListeners(){

    }
}
