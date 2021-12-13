package com.umut.videostream.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

// Scenes are only reachable by a View instance
class InitialScene extends JFrame {
    final JButton createAccountButton;
    final JButton logInButton;
    Container container;


    public InitialScene(){
        createAccountButton = new JButton("Create Account");
        logInButton = new JButton("Login");

        container = getContentPane();

        container.add(createAccountButton);
        container.add(logInButton);

        container.setLayout(new FlowLayout());
        setLocationRelativeTo(null);
        setResizable(false);

        setSize(300,300);
        setVisible(true);
    }

    public void addCreatAccountListener(ActionListener listener){
        createAccountButton.addActionListener(listener);

    }

    public void addLogInListener(ActionListener listener){
        logInButton.addActionListener(listener);
    }
}
