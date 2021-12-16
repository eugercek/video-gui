package com.umut.videostream.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

// Scenes are only reachable by a View instance
public class InitialScene extends JFrame implements IFreezable {
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

        FlowLayout layout = new FlowLayout();
        layout.setAlignment(FlowLayout.CENTER);

        container.setLayout(layout);

        setSize(800, 600);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

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
