package main.java.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuPanel extends JPanel {
    public JButton inventory;
    public JButton orders;
    private JButton providers;
    private JButton reports;
    private JButton settings;

    public MenuPanel(){
        Dimension dim = getPreferredSize();
        dim.width = 250;
        setPreferredSize(dim);

        inventory = new JButton("Inventory");
        orders = new JButton("Orders");
        providers = new JButton("Providers");
        reports = new JButton("Reports");
        settings = new JButton("settings");


        setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();
        gc.fill = GridBagConstraints.NONE;

        /////// First row ///////
        gc.weightx = 1;
        gc.weighty = 0.5;

        gc.gridx = 0;
        gc.gridy = 0;
        gc.anchor = GridBagConstraints.CENTER;
        add(inventory, gc);

        /////// Second row ///////
        gc.weightx = 1;
        gc.weighty = 0.5;

        gc.gridx = 0;
        gc.gridy ++;
        gc.anchor = GridBagConstraints.CENTER;
        add(orders, gc);

        /////// Third row ///////
        gc.weightx = 1;
        gc.weighty = 0.5;

        gc.gridx = 0;
        gc.gridy ++;
        gc.anchor = GridBagConstraints.CENTER;
        add(providers,gc);

        /////// Fourth row ///////
        gc.weightx = 1;
        gc.weighty = 0.5;

        gc.gridx = 0;
        gc.gridy ++;
        gc.anchor = GridBagConstraints.CENTER;
        add(reports,gc);

        /////// Fifth row ///////
        gc.weightx = 1;
        gc.weighty = 0.5;

        gc.gridx = 0;
        gc.gridy ++;
        gc.anchor = GridBagConstraints.CENTER;
        add(settings,gc);
    }
}
