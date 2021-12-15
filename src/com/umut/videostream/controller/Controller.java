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
    }

    private void bindInitialSceneHandlers(){
        view.getInitialScene()
                .getCreateAccountButton()
                .addActionListener(e -> switchToCreateAccountScene());

        view.getInitialScene()
                .getLogInButton()
                .addActionListener(e -> switchLoginScene());
    }

}
