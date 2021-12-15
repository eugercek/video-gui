package com.umut.videostream.controller;

import com.umut.videostream.view.View;
import com.umut.videostream.model.Model;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.nio.channels.spi.AbstractInterruptibleChannel;

public class Controller {
    View view;
    Model model;

    public  Controller(Model model, View view){
        this.model = model;
        this.view = view;
        bindEventHandlers();

        view.createInitialWindow();

        try {
            model.getRepo().connectDatabase();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
           serverConnectionError(1, "Server connection error! please try 3 seconds later.");
        }
    }

    public void switchLoginScene(){
        view.getInitialScene().setVisible(false);
        view.getLoginScene().setVisible(true);
    }

    public void switchToCreateAccountScene(){
        view.getInitialScene().setVisible(false);
        view.getCreateAccountScene().setVisible(true);
    }

    private void bindEventHandlers(){
        bindInitialSceneHandlers();
        bindLoginSceneHandlers();
    }

    private void bindInitialSceneHandlers(){
        view.getInitialScene()
                .getCreateAccountButton()
                .addActionListener(e -> switchToCreateAccountScene());

        view.getInitialScene()
                .getLogInButton()
                .addActionListener(e -> switchLoginScene());
    }

    private  void bindLoginSceneHandlers(){
        view.getLoginScene()
                .getSubmitButton()
                .addActionListener(e -> logIn());

        view.getCreateAccountScene()
                .getSubmitButton()
                .addActionListener(e -> createAccount());

    }

    public void logIn(){
        final String username = view.getLoginScene().getUsernameValue();
        final String password = view.getLoginScene().getPasswordValue();
        System.out.println(username + " " + password);
    }

    public void createAccount(){
        final String name = view.getCreateAccountScene().getNameValue();
        final String surname = view.getCreateAccountScene().getSurnameValue();
        final String email = view.getCreateAccountScene().getEmailValue();
        final String username = view.getCreateAccountScene().getUsernameValue();
        final String password = view.getCreateAccountScene().getPasswordValue();

        System.out.println(name + " " + surname + " " + email + " " + username + " " + password);
    }
    public void serverConnectionError(int seconds, String message){
        view.blockInitialScreen();

        JOptionPane.showMessageDialog(null, "Server connection error: " + message, "Connection Error", JOptionPane.WARNING_MESSAGE);

        ActionListener listener = new ActionListener(){
            public void actionPerformed(ActionEvent e){
                view.activateInitialScreen();
            }
        };
        
        Timer timer = new Timer(seconds * 1000, listener);
        timer.setRepeats(false);
        timer.start();
    }
}
