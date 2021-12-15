package com.umut.videostream.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class CreateAccountScene extends JFrame implements IFreezable{
    private JTextField nameTextField, surnameTextField, emailTextField, usernameTextField,passwordTextField;
    private JLabel nameLabel, surnameLabel, emailLabel, usernameLabel, passwordLabel;
    private Container container;
    private JFrame caller;
    private JButton submitButton;

    public CreateAccountScene(JFrame caller){
        this.caller = caller;
        nameLabel = new JLabel("Username");
        nameTextField = new JTextField(20);

        surnameLabel = new JLabel("Surname");
        surnameTextField = new JTextField(20);

        emailLabel = new JLabel("Email");
        emailTextField = new JTextField(20);

        passwordLabel = new JLabel("Password");
        passwordTextField = new JTextField(20);

        usernameLabel = new JLabel("Username");
        usernameTextField = new JTextField(20);

        submitButton = new JButton("Create Account");

        container = getContentPane();

        container.add(nameLabel);
        container.add(nameTextField);

        container.add(surnameLabel);
        container.add(surnameTextField);

        container.add(emailLabel);
        container.add(emailTextField);

        container.add(usernameLabel);
        container.add(usernameTextField);

        container.add(passwordLabel);
        container.add(passwordTextField);

        container.add(submitButton);

        container.setLayout(new FlowLayout());

        setSize(600,800);
        // setResizable(false);
        setLocationRelativeTo(null);


        addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e){
                caller.setVisible(true);
            }
        });
    }

    public String getNameValue(){
        return nameTextField.getText();
    }

    public String getSurnameValue(){
        return surnameTextField.getText();
    }

    public String getEmailValue(){
        return emailTextField.getText();
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
