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

    public MainForm() {
        super("Restaurant Manager");
        ImageIcon img = new ImageIcon("src/main/java/GUI/img/icon.png"); //TODO: switch with constant of relative path (src/main/java...)
        setIconImage(img.getImage());
        cl = new CardLayout();
        containerPanel = new JPanel();
        inventoryPanel = new InventoryPanel();
        ordersSearchPanel = new OrdersSearchPanel();
        ordersAddPanel = new OrdersAddPanel();
        OrdersTabs = new JTabbedPane();
        menuPanel = new MenuPanel();

        //////////////// set the layout ////////////////
        OrdersTabs.add("Search Orders", ordersSearchPanel);
        OrdersTabs.add("Add Order", ordersAddPanel);

        containerPanel.setLayout(cl);
        containerPanel.add(inventoryPanel, "Inventory");
        containerPanel.add(OrdersTabs, "Orders");
        cl.show(containerPanel, "Inventory");

        ////////////// Menu items listeners ////////////////
        menuPanel.inventory.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cl.show(containerPanel, "Inventory");
            }
        });
        menuPanel.orders.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cl.show(containerPanel, "Orders");
            }
        });


        add(menuPanel, BorderLayout.WEST);
        add(containerPanel, BorderLayout.CENTER);
        setScreenSize();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setResizable(false);



    }

    private void setScreenSize() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize((int) (screenSize.width*0.9), (int) (screenSize.height*0.9));
    }

}
