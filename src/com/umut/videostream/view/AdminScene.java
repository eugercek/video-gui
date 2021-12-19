package com.umut.videostream.view;

import com.umut.videostream.model.enums.ESubscriptionType;

import javax.swing.*;
import java.awt.*;

public class AdminScene extends JFrame implements IFreezable {
    private final JComboBox typeComboBox;
    private final JTextField userNameTextField;
    private final JComboBox<String> subscriptionTypeComboBox;
    private final JButton submitButton;
    private final Container container;

    public AdminScene() {
        typeComboBox = new JComboBox(new String[]{"User ID", "Subscription Type"});
        userNameTextField = new JTextField(20);

        subscriptionTypeComboBox = new JComboBox(new String[]{
                ESubscriptionType.FREE.name(),
                ESubscriptionType.MEDIUM.name(),
                ESubscriptionType.VIP.name()});

        submitButton = new JButton("Generate report");

        container = getContentPane();

        container.add(typeComboBox);
        container.add(userNameTextField);

        subscriptionTypeComboBox.setVisible(false);
        container.add(subscriptionTypeComboBox);
        container.add(submitButton);

        container.setLayout(new FlowLayout());

        setSize(600, 800);
        setResizable(false);
        setLocationRelativeTo(null);
    }

    @Override
    public void freezeScene() {

    }

    @Override
    public void unfreezeScene() {

    }

    public JButton getSubmitButton() {
        return submitButton;
    }

    public JTextField getUserNameTextField() {
        return userNameTextField;
    }

    public boolean isIdMode() {
        return typeComboBox.getSelectedItem().toString().equals("User ID");
    }

    public JComboBox<String> getSubscriptionTypeComboBox() {
        return subscriptionTypeComboBox;
    }

    public JComboBox getTypeComboBox() {
        return typeComboBox;
    }


}
