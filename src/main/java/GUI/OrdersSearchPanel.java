package main.java.GUI;

import javax.swing.*;
import java.awt.*;

public class OrdersSearchPanel extends JPanel {
    private JButton searchItemButton;
    private JButton placeOrder;
    public OrdersSearchPanel(){

        searchItemButton = new JButton("Search item");
        placeOrder = new JButton("Place order ");

        setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();
        gc.fill = GridBagConstraints.NONE;


        /////// First row ///////
        gc.weightx = 1;
        gc.weighty = 0.5;

        gc.gridx = 0;
        gc.gridy = 0;
        gc.anchor = GridBagConstraints.CENTER;
        add(searchItemButton, gc);

        /////// Second row ///////
        gc.weightx = 1;
        gc.weighty = 0.5;

        gc.gridx = 0;
        gc.gridy = 1;
        gc.anchor = GridBagConstraints.CENTER;
        add(placeOrder, gc);
    }

}
