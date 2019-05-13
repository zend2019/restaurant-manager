package main.java.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuPanel extends JPanel {
    private JButton inventory;
    private JButton orders;
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

        //TODO: Clicking will lead to corresponding page
        inventory.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Inventory clicked");
            }
        });

        orders.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Orders clicked");
            }
        });

        providers.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Providers clicked");
            }
        });

        reports.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Reports clicked");
            }
        });

        settings.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Settings clicked");
            }
        });

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
        gc.gridy = 1;
        gc.anchor = GridBagConstraints.CENTER;
        add(orders, gc);

        /////// Third row ///////
        gc.weightx = 1;
        gc.weighty = 0.5;

        gc.gridx = 0;
        gc.gridy = 2;
        gc.anchor = GridBagConstraints.CENTER;
        add(providers,gc);

        /////// Fourth row ///////
        gc.weightx = 1;
        gc.weighty = 0.5;

        gc.gridx = 0;
        gc.gridy = 3;
        gc.anchor = GridBagConstraints.CENTER;
        add(reports,gc);

        /////// Fifth row ///////
        gc.weightx = 1;
        gc.weighty = 0.5;

        gc.gridx = 0;
        gc.gridy = 4;
        gc.anchor = GridBagConstraints.CENTER;
        add(settings,gc);
    }
}
