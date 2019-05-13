package main.java.GUI;

import javax.swing.*;
import java.awt.*;

public class MainForm extends JFrame {
    private WorkPanel workPanel;
    private MenuPanel menuPanel;

    public  MainForm(){
        workPanel = new WorkPanel();
        menuPanel = new MenuPanel();

        add(menuPanel, BorderLayout.WEST);
        add(workPanel,BorderLayout.CENTER);

        setSize(800,600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

}
