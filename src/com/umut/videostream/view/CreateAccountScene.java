package com.umut.videostream.view;

import com.umut.videostream.model.enums.ESubscriptionType;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class CreateAccountScene extends JFrame implements IFreezable {
    private final JTextField nameTextField;
    private final JTextField surnameTextField;
    private final JTextField emailTextField;
    private final JTextField usernameTextField;
    private final JPasswordField passwordTextField;
    private final JPasswordField creditCardPassworTexfField;
    private final JLabel nameLabel;
    private final JLabel surnameLabel;
    private final JLabel emailLabel;
    private final JLabel usernameLabel;
    private final JLabel passwordLabel;
    private final JLabel creditCardPasswordLabel;
    private final JPanel panel;
    private final JFrame caller;
    private final JButton submitButton;

    private final JComboBox<ESubscriptionType> comboBox;

    public CreateAccountScene(JFrame caller, ESubscriptionType[] types) {
        this.caller = caller;
        nameLabel = new JLabel("Username");
        nameTextField = new JTextField(20);

        surnameLabel = new JLabel("Surname");
        surnameTextField = new JTextField(20);

        emailLabel = new JLabel("Email");
        emailTextField = new JTextField(20);

        passwordLabel = new JLabel("Password");
        passwordTextField = new JPasswordField(20);

        usernameLabel = new JLabel("Username");
        usernameTextField = new JTextField(20);

        submitButton = new JButton("Create Account");

        comboBox = new JComboBox<ESubscriptionType>();

        creditCardPasswordLabel = new JLabel("Credit card password: ");
        creditCardPassworTexfField = new JPasswordField(20);

        creditCardPasswordLabel.setVisible(false);
        creditCardPassworTexfField.setVisible(false);

        for (var type : types) {
            comboBox.addItem(type);
        }

        panel = new JPanel(new FlowLayout());

        panel.add(nameLabel);
        panel.add(nameTextField);

        panel.add(surnameLabel);
        panel.add(surnameTextField);

        panel.add(emailLabel);
        panel.add(emailTextField);

        panel.add(usernameLabel);
        panel.add(usernameTextField);

        panel.add(passwordLabel);
        panel.add(passwordTextField);

        panel.add(creditCardPasswordLabel);
        panel.add(creditCardPassworTexfField);

        JPanel div = new JPanel();
        div.add(comboBox);
        div.add(submitButton);
        div.setBorder(new EmptyBorder(10, 0, 0, 0));

        panel.add(div);

        getContentPane().add(panel);

        setSize(250, 500);
        setTitle("Create account");
        setResizable(true);
        setLocationRelativeTo(null);


        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                caller.setVisible(true);
            }
        });
    }

    public String getNameValue() {
        return nameTextField.getText();
    }

    public String getSurnameValue() {
        return surnameTextField.getText();
    }

    public String getEmailValue() {
        return emailTextField.getText();
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

    public JComboBox<ESubscriptionType> getComboBox() {
        return comboBox;
    }

    public JPasswordField getCreditCardPasswordTextField() {
        return creditCardPassworTexfField;
    }

    public JLabel getCreditCardPasswordLabel() {
        return creditCardPasswordLabel;
    }

    public void activateCreditCardSection() {
        creditCardPasswordLabel.setVisible(true);
        creditCardPassworTexfField.setVisible(true);
    }

    public void deActivateCreditCardSection() {
        creditCardPasswordLabel.setVisible(false);
        creditCardPassworTexfField.setVisible(false);
    }
}
