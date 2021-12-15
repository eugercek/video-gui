package com.umut.videostream.view;

import javax.swing.*;
import java.awt.*;

public class AdminScene extends JFrame implements IFreezable {
    private JComboBox comboBox;
    private JTextField userNameTextField;
    private Container container;

    public AdminScene(){
        comboBox = new JComboBox(new String[]{"User ID", "Subscription Type"});
        userNameTextField = new JTextField(20);

        container = getContentPane();

        container.add(comboBox);
        container.add(userNameTextField);

        container.setLayout(new FlowLayout());

        setSize(600,800);
        setResizable(false);
        setLocationRelativeTo(null);

    }

    @Override
    public void freezeScene() {

    }

    @Override
    public void unfreezeScene() {

    }
}
