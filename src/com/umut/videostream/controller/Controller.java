package com.umut.videostream.controller;

import com.umut.videostream.view.View;
import com.umut.videostream.model.Model;

public class Controller {
    View view;
    Model model;

    public  Controller(Model model, View view){
        this.model = model;
        this.view = view;

    }
}
