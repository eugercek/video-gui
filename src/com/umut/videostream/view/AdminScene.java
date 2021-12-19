package com.umut.videostream.view;

import com.umut.videostream.model.enums.EMovieGenre;
import com.umut.videostream.model.enums.ESubscriptionType;

import javax.swing.*;
import java.awt.*;

public class AdminScene extends JFrame implements IFreezable {
    private final JComboBox comboBox;
    private final JTextField userNameTextField;
    private final JComboBox<ESubscriptionType> subscriptionTypeComboBox;
    private final JButton submitButton;
    private final Container container;
    private  boolean idMode;

    public AdminScene() {
        comboBox = new JComboBox(new String[]{"User ID", "Subscription Type"});
        userNameTextField = new JTextField(20);

        subscriptionTypeComboBox = new JComboBox<ESubscriptionType>(new ESubscriptionType[]{
                ESubscriptionType.FREE,
                ESubscriptionType.MEDIUM,
                ESubscriptionType.VIP});

        submitButton = new JButton("Generate report");

        container = getContentPane();

        container.add(comboBox);
        container.add(userNameTextField);
        container.add(subscriptionTypeComboBox);

        // At first Search by user id is usable
        subscriptionTypeComboBox.setVisible(false);
        idMode = true;

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
        return idMode;
    }

    public JComboBox<ESubscriptionType> getSubscriptionTypeComboBox() {
        return subscriptionTypeComboBox;
    }

}
