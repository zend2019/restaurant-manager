package main.java.GUI;

import main.java.common.constants.Constants;
import main.java.common.constants.GUIConstants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static java.lang.Integer.valueOf;

public class EditItemDialog extends JDialog {
    private JLabel numOfItemsLabel = new JLabel("Items to add? ");
    private JLabel exceedLimit = new JLabel(GUIConstants.EXCEED_LIMIT);
    private JButton addButton = new JButton("Add");
    private DialogListener dialogListener;
    public JTextField numOfItemsTF = new JTextField();

    public EditItemDialog(JFrame parent){
        super(parent,"Edit item",false);
        setLayout(parent);
        setActionListeners();
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

        add(exceedLimit,gcMain);
        exceedLimit.setForeground(Color.red);
        exceedLimit.setVisible(false);

        ///// align fields sizes //////
        Dimension dim = new Dimension(60,7);
        addButton.setPreferredSize(dim);
        numOfItemsTF.setPreferredSize(dim);
    }

    private void setActionListeners(){
        setAddButton();
    }

    private void setAddButton() {
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int itemsToAdd = valueOf(numOfItemsTF.getText());
                //TODO: function that checks the items entered do not exceed limit
                if(dialogListener != null){
                    dialogListener.setItemInOrder(itemsToAdd);
                }
                dispose();
            }
        });
    }

    //Used tp pass information from Dialog back to the Panel
    public void setItemDialogListener(DialogListener dialogListener) {
        this.dialogListener = dialogListener;
    }
}
