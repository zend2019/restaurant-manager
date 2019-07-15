package main.java.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuPanel extends JPanel {
    public JButton inventory;
    public JButton orders;
    public JButton reports;
    public JButton settings;

    public MenuPanel(){
        Dimension dim = getPreferredSize();
        dim.width = 140;
        setPreferredSize(dim);
        inventory = new JButton("Inventory");
        orders = new JButton("Orders");
        reports = new JButton("Reports");
        settings = new JButton("Settings");

        setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();
        gc.fill = GridBagConstraints.NONE;

        /////// First row ///////
        gc.weightx = 1;
        gc.weighty = 0.1;

        gc.gridx = 0;
        gc.gridy = 0;
        gc.anchor = GridBagConstraints.CENTER;
        add(inventory, gc);

        /////// Second row ///////
        gc.weightx = 1;
        gc.weighty = 0.1;

        gc.gridx = 0;
        gc.gridy ++;
        gc.anchor = GridBagConstraints.CENTER;
        add(orders, gc);

        /////// Third row ///////
        gc.weightx = 1;
        gc.weighty = 0.1;

        gc.gridx = 0;
        gc.gridy ++;
        gc.anchor = GridBagConstraints.CENTER;
        add(reports,gc);

        /////// Four row ///////
        gc.weightx = 1;
        gc.weighty = 2;

        gc.gridx = 0;
        gc.gridy ++;
        gc.anchor = GridBagConstraints.PAGE_END;
        add(settings,gc);


        //align sizes
        Dimension buttonDim = inventory.getPreferredSize();
        orders.setPreferredSize(buttonDim);
        reports.setPreferredSize(buttonDim);
        settings.setPreferredSize(buttonDim);

        this.setBackground(Color.GREEN);
        //this.setBounds(1,1,100,100);
        //setSize(350, 250);
    }
}
