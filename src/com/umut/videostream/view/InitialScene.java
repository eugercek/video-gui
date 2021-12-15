package com.umut.videostream.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

// Scenes are only reachable by a View instance
public class InitialScene extends JFrame {
    private JButton createAccountButton;
    private JButton logInButton;
    private Container container;

    public JButton getCreateAccountButton() {
        return createAccountButton;
    }

    public JButton getLogInButton() {
        return logInButton;
    }

    public InitialScene() {
        createAccountButton = new JButton("Create Account");
        logInButton = new JButton("Login");

        container = getContentPane();

        container.add(createAccountButton);
        container.add(logInButton);

        container.setLayout(new FlowLayout());

        setSize(300, 300);
        setResizable(false);
        setLocationRelativeTo(null);

    }

    public void addCreatAccountListener(ActionListener listener) {
        createAccountButton.addActionListener(listener);

    }

    public void addLogInListener(ActionListener listener) {
        logInButton.addActionListener(listener);
    }
}
