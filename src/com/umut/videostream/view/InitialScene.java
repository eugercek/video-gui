package com.umut.videostream.view;

import javax.swing.*;
import java.awt.*;

// Scenes are only reachable by a View instance
class InitialScene extends JFrame {
    private JButton createAccount, logIn;
    Container container;

    public InitialScene(){
        createAccount = new JButton("Create Account");
        logIn = new JButton("Login");

        container = getContentPane();

        container.add(createAccount);
        container.add(logIn);

        container.setLayout(new FlowLayout());
        setLocationRelativeTo(null);
        setResizable(false);

        setSize(300,300);
        setVisible(true);
    }
}
