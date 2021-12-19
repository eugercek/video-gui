package com.umut.videostream.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class LoginScene extends JFrame implements IFreezable {
    private final JTextField usernameTextField;
    private final JPasswordField passwordTextField;
    private final JLabel usernameLabel;
    private final JLabel passwordLabel;
    private final Container container;
    private final JFrame caller;

    private final JButton submitButton;

    public LoginScene(JFrame caller) {
        this.caller = caller;
        usernameLabel = new JLabel("Username");
        usernameTextField = new JTextField(20);

        passwordLabel = new JLabel("Password");
        passwordTextField = new JPasswordField(20);

        submitButton = new JButton("Login");

        container = getContentPane();

        container.add(usernameLabel);
        container.add(usernameTextField);

        container.add(passwordLabel);
        container.add(passwordTextField);

        container.add(submitButton);

        container.setLayout(new FlowLayout());

        setSize(250, 250);
        setTitle("Login");
        setResizable(false);
        setLocationRelativeTo(null);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                caller.setVisible(true);
            }
        });
    }

    public String getUsernameValue() {
        return usernameTextField.getText();
    }

    public String getPasswordValue() {
        return String.valueOf(passwordTextField.getPassword());
    }

    public JButton getSubmitButton() {
        return submitButton;
    }

    @Override
    public void freezeScene() {
        submitButton.setEnabled(false);
    }

    @Override
    public void unfreezeScene() {
        submitButton.setEnabled(true);
    }
}
