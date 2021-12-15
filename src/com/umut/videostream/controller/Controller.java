package com.umut.videostream.controller;

import com.umut.videostream.view.View;
import com.umut.videostream.model.Model;

public class Controller {
    View view;
    Model model;

    public  Controller(Model model, View view){
        this.model = model;
        this.view = view;
        bindEventHandlers();

        view.createInitialWindow();
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
}
