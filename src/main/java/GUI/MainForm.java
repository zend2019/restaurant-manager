package main.java.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainForm extends JFrame {
    private JPanel containerPanel;
    private InventoryPanel inventoryPanel;
    private OrdersSearchPanel ordersPanel;
    private MenuPanel menuPanel;
    private CardLayout cl;

    public  MainForm(){
        super("Main form");
        cl = new CardLayout();
        containerPanel = new JPanel();
        inventoryPanel = new InventoryPanel();
        ordersPanel = new OrdersSearchPanel();
        menuPanel = new MenuPanel();

     //////////////// set the layout ////////////////
        containerPanel.setLayout(cl);
        containerPanel.add(inventoryPanel,"Inventory");
        containerPanel.add(ordersPanel,"Orders");
        cl.show(containerPanel,"Inventory");

     ////////////// Menu items listeners ////////////////
        menuPanel.inventory.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               cl.show(containerPanel,"Inventory");
            }
        });
        menuPanel.orders.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cl.show(containerPanel,"Orders");
            }
        });


        add(menuPanel, BorderLayout.WEST);
        add(containerPanel,BorderLayout.CENTER);

        setSize(900,700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);


    }

}
