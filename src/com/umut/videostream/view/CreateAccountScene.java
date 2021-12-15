package com.umut.videostream.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class CreateAccountScene extends JFrame{
    private JTextField nameTextField, surnameTextField, emailTextField;
    private JLabel nameLabel, surnameLabel, emailLabel;
    private Container container;
    private JFrame caller;

    public CreateAccountScene(JFrame caller){
        this.caller = caller;
        nameLabel = new JLabel("Username");
        nameTextField = new JTextField(20);

        surnameLabel = new JLabel("Surname");
        surnameTextField = new JTextField(20);

        emailLabel = new JLabel("Email");
        emailTextField = new JTextField(20);

        container = getContentPane();

        container.add(nameLabel);
        container.add(nameTextField);

        container.add(surnameLabel);
        container.add(surnameTextField);

        container.add(emailLabel);
        container.add(emailTextField);


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
