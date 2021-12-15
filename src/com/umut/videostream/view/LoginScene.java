package com.umut.videostream.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class LoginScene extends JFrame  implements IFreezable{
    private JTextField usernameTextField, passwordTextField;
    private JLabel usernameLabel, passwordLabel;
    private Container container;
    private JFrame caller;

    private JButton submitButton;

    public LoginScene(JFrame caller){
        this.caller = caller;
        usernameLabel = new JLabel("Username");
        usernameTextField = new JTextField(20);

        passwordLabel = new JLabel("Password");
        passwordTextField = new JTextField(20);

        submitButton = new JButton("Login");

        container = getContentPane();

        container.add(usernameLabel);
        container.add(usernameTextField);

        container.add(passwordLabel);
        container.add(passwordTextField);

        container.add(submitButton);

        container.setLayout(new FlowLayout());

        setSize(600,800);
        setResizable(false);
        setLocationRelativeTo(null);

        addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e){
                caller.setVisible(true);
            }
        });
    }

    public String getUsernameValue(){
        return usernameTextField.getText();
    }

    public String getPasswordValue(){
        return passwordTextField.getText();
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
