package com.umut.videostream.view;

import com.umut.videostream.model.Movie;
import com.umut.videostream.model.enums.EMovieGenre;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class MovieScene extends JFrame implements IFreezable {
    Container container;
    public MovieScene() {
        container = getContentPane();

        GridLayout layout = new GridLayout(3,3);

        container.setLayout(layout);

        setSize(800, 600);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void freezeScene() {

    }

    @Override
    public void unfreezeScene() {

    }

    public void renderMovie(String name, Image image){
        JLabel label = new JLabel(new ImageIcon(image));
        container.add(label);
    }
}
