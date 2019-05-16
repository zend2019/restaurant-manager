package main.java.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainForm extends JFrame {
    private JPanel containerPanel;
    private InventoryPanel inventoryPanel;
    private OrdersSearchPanel ordersSearchPanel;
    private OrdersAddPanel ordersAddPanel;
    private JTabbedPane OrdersTabs;
    private MenuPanel menuPanel;
    private CardLayout cl;

    public  MainForm(){
        super("Main form");
        cl = new CardLayout();
        containerPanel = new JPanel();
        inventoryPanel = new InventoryPanel();
        ordersSearchPanel = new OrdersSearchPanel();
        ordersAddPanel = new OrdersAddPanel();
        OrdersTabs = new JTabbedPane();
        menuPanel = new MenuPanel();

     //////////////// set the layout ////////////////
        OrdersTabs.add("Search Orders", ordersSearchPanel);
        OrdersTabs.add("Add Order",ordersAddPanel);

        containerPanel.setLayout(cl);
        containerPanel.add(inventoryPanel,"Inventory");
        containerPanel.add(OrdersTabs,"Orders");
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
