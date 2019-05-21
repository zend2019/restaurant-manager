package main.java.GUI;

import javax.swing.*;
import java.awt.*;

public class OrderPlacedDialog extends JDialog {
    private Integer placedOrderId;
    private JLabel orderIdLabel = new JLabel();
    private JLabel orderSetLabel = new JLabel("Your order has been placed.");

    public OrderPlacedDialog(JFrame parent) {
        super(parent, "Order in process", false);
        setLayout(parent);
    }

    public void setPlacedOrderId(Integer orderId){
        placedOrderId = orderId;
        setOrderIdLabel();
    }
    private Integer getPlacedOrderId(){
        return placedOrderId;
    }

    private void setOrderIdLabel(){
        orderIdLabel.setText("The order ID is: " + getPlacedOrderId());
    }

    private void setLayout(JFrame parent) {
        setLocationRelativeTo(parent);
        setSize(250,150);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        setLayout(new GridBagLayout());
        GridBagConstraints gcMain = new GridBagConstraints();
        gcMain.fill = GridBagConstraints.RELATIVE;

        //First Row//
        gcMain.ipady = 5;
        gcMain.gridy = 0;
        gcMain.weightx = 1;
        gcMain.weighty = 1;

        gcMain.gridx = 0;
        gcMain.anchor = GridBagConstraints.CENTER;
        add(orderSetLabel,gcMain);

        gcMain.gridy = 1;
        add(orderIdLabel,gcMain);

    }


}
