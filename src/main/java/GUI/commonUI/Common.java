package main.java.GUI.commonUI;

import java.awt.*;

public class Common {

    public static void FirstRow(GridBagConstraints panel) {
        panel.gridy = 0;
        panel.weightx = 1;
        panel.weighty = 0.1;
        panel.gridx = 0;
    }

    public static void NextRow(GridBagConstraints panel,double weigthx, double weigthy, int gridx) {
        panel.gridy++;
        panel.weightx = weigthx;
        panel.weighty = weigthy;
        panel.gridx = gridx;
    }

}
