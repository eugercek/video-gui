package com.umut.videostream.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class LoginScene extends JFrame {
    private JTextField userNameTextField, passwordTextField;
    private JLabel userNameLabel, passwordLabel;
    private Container container;
    private JFrame caller;

    public LoginScene(JFrame caller){
        this.caller = caller;
        userNameLabel = new JLabel("Username");
        userNameTextField = new JTextField(20);

        passwordLabel = new JLabel("Password");
        passwordTextField = new JTextField(20);

        container = getContentPane();

        container.add(userNameLabel);
        container.add(userNameTextField);

        container.add(passwordLabel);
        container.add(passwordTextField);

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
}
