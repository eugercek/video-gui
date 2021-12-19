package com.umut.videostream.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

// Scenes are only reachable by a View instance
public class InitialScene extends JFrame implements IFreezable {
    private final JButton createAccountButton;
    private final JButton logInButton;
    private final Container container;

    public InitialScene() {
        createAccountButton = new JButton("Create Account");
        logInButton = new JButton("Login");

        container = getContentPane();
        container.setLayout(null);

        createAccountButton.setBounds(100, 170, 200, 30);
        logInButton.setBounds(400, 170, 100, 30);

        container.add(createAccountButton);
        container.add(logInButton);

        setSize(600, 400);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public JButton getCreateAccountButton() {
        return createAccountButton;
    }

    public JButton getLogInButton() {
        return logInButton;
    }

    public void addCreatAccountListener(ActionListener listener) {
        createAccountButton.addActionListener(listener);

    }

    public void addLogInListener(ActionListener listener) {
        logInButton.addActionListener(listener);
    }

    @Override
    public void freezeScene() {
        createAccountButton.setEnabled(false);
        logInButton.setEnabled(false);

    }

    @Override
    public void unfreezeScene() {
        createAccountButton.setEnabled(true);
        logInButton.setEnabled(true);

    }
}
