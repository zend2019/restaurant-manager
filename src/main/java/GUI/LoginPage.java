package main.java.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class LoginPage extends JFrame{
    private JPanel LoginPanel;
    private JButton loginButton;
    private JLabel userNameLabel;
    private JTextField userNameInput;
    private JLabel passwordLabel;
    private JPasswordField passwordInput;
    private JLabel validationMessage;

    public LoginPage() {
        super("Login form");

        loginButton = new JButton("Login");
        //loginButton.setBorder(BorderFactory.createEmptyBorder());
        //loginButton.setContentAreaFilled(false);
        userNameLabel = new JLabel("User Name: ");
        userNameInput = new JTextField(15);
        passwordLabel = new JLabel("Password: ");
        passwordInput = new JPasswordField(15);

        //TODO: Add user password validations
        //TODO: Clicking will close and lead to new page
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Login clicked");
            }
        });

        setSize(500,500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();
        gc.fill = GridBagConstraints.NONE;

        /////// First row ///////
        gc.weightx = 1;
        gc.weighty = 0.1;

        gc.gridx = 0;
        gc.gridy = 0;
        gc.anchor = GridBagConstraints.LINE_END;
        add(userNameLabel, gc);

        gc.gridx = 1;
        gc.gridy = 0;
        gc.anchor = GridBagConstraints.LINE_START;
        add(userNameInput, gc);

        /////// Second row ///////
        gc.weightx = 1;
        gc.weighty = 0.1;

        gc.gridx = 0;
        gc.gridy = 1;
        gc.anchor = GridBagConstraints.LINE_END;
        add(passwordLabel, gc);

        gc.gridx = 1;
        gc.gridy = 1;
        gc.anchor = GridBagConstraints.LINE_START;
        add(passwordInput, gc);

        /////// Third row ///////
        gc.weightx = 1;
        gc.weighty = 1.5;

        gc.gridx = 1;
        gc.gridy = 2;
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        add(loginButton,gc);
    }

}
