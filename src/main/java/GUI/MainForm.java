package main.java.GUI;

import main.java.BL.Contract.Manager;
import main.java.BL.Contract.User;
import main.java.common.constants.Constants;

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
    private JTabbedPane settingTabs;
    private JTabbedPane reportsTabs;
    private MenuPanel menuPanel;
    private DailyReportPanel dailyReportPanel;
    private OutOfStockReport outOfStockReport;
    private CardLayout cl;
    private User user;
    private UserPanel userPanel;

    public MainForm(User logInUser) throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {
        super("Restaurant Manager");
        UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");

        ImageIcon img = new ImageIcon(Constants.LOGO_IMAGE);
        setIconImage(img.getImage());
        //getContentPane().setBackground(Color.PINK);

        cl = new CardLayout();
        containerPanel = new JPanel();
        inventoryPanel = new InventoryPanel();
        ordersSearchPanel = new OrdersSearchPanel();
        ordersAddPanel = new OrdersAddPanel();
        OrdersTabs = new JTabbedPane();
        menuPanel = new MenuPanel();
        dailyReportPanel = new DailyReportPanel();
        reportsTabs = new JTabbedPane();
        outOfStockReport = new OutOfStockReport();
        userPanel = new UserPanel();
        settingTabs = new JTabbedPane();
        user = logInUser;

        //////////////// set the layout ////////////////
        OrdersTabs.add("Search Orders", ordersSearchPanel);
        OrdersTabs.add("Add Order", ordersAddPanel);

        reportsTabs.add("Daily Report", dailyReportPanel);
        reportsTabs.add("Out of stock Report", outOfStockReport);

        settingTabs.add("Add New User", userPanel);

        containerPanel.setLayout(cl);
        containerPanel.add(inventoryPanel, "Inventory");
        containerPanel.add(OrdersTabs, "Orders");
        containerPanel.add(reportsTabs, "Reports");

        if (user instanceof Manager) {
            containerPanel.add(settingTabs, "Settings");
        }
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
        menuPanel.reports.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cl.show(containerPanel, "Reports");
            }
        });
        menuPanel.settings.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cl.show(containerPanel, "Settings");
            }
        });


        add(menuPanel, BorderLayout.WEST);
        add(containerPanel, BorderLayout.CENTER);
        setScreenSize();
        setMenuPanelSize();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setResizable(false);


    }

    private void setScreenSize() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(1000, (int) (screenSize.height * 0.9));
    }
//screenSize.width
    private void setMenuPanelSize() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension dim = new Dimension(120, (int) (screenSize.height * 0.9));
        menuPanel.setPreferredSize(dim);
    }

}
