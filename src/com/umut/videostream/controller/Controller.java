package com.umut.videostream.controller;

import com.umut.videostream.model.Movie;
import com.umut.videostream.model.User;
import com.umut.videostream.model.enums.EMovieGenre;
import com.umut.videostream.model.exceptions.SubscriptionTypeNotFound;
import com.umut.videostream.model.exceptions.UserNotFoundException;
import com.umut.videostream.model.repository.tmdb.MovieTMDBRepository;
import com.umut.videostream.model.services.NetworkOperations;
import com.umut.videostream.view.IFreezable;
import com.umut.videostream.view.View;
import com.umut.videostream.model.Model;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class Controller {
    View view;
    Model model;

    public Controller(Model model, View view) {
        this.model = model;
        this.view = view;
        bindEventHandlers();

        view.createInitialWindow();
    }

    public void switchLoginScene() {
        view.getInitialScene().setVisible(false);
        view.getLoginScene().setVisible(true);
    }

    public void switchToCreateAccountScene() {
        view.getInitialScene().setVisible(false);
        view.getCreateAccountScene().setVisible(true);
    }

    public void switchToVideoScene() {
        view.getLoginScene().setVisible(false);
        view.getMovieScene().setVisible(true);


        loadInitialMovieState();
    }

    private void loadMovies(EMovieGenre genre) {
        try {
            Movie[] movies = model.getMovieRepository().getMoviesByGenre(genre);
            for (Movie movie : movies) {
                Image image = NetworkOperations.downloadImage(MovieTMDBRepository.getPosterURL(300, movie.getContentPath()));
                view.getMovieScene().renderMovie("Title", image);
            }
        } catch (IOException e) {
            serverConnectionError(1, "Server error", view.getMovieScene());
            e.printStackTrace();
        }
    }

    private void loadInitialMovieState(){
        var userGenres = EMovieGenre.getGenreListBySubscriptionType(model.getActiveUser().getSubscriptionType());
        var randomGenre = EMovieGenre.getRandomGenre(userGenres);
        System.out.println(randomGenre);

        view.getMovieScene().renderComboBox(userGenres, randomGenre);
        loadMovies(randomGenre);
    }

    private void bindEventHandlers() {
        bindInitialSceneHandlers();
        bindLoginSceneHandlers();
    }

    private void bindInitialSceneHandlers() {
        view.getInitialScene()
                .getCreateAccountButton()
                .addActionListener(e -> switchToCreateAccountScene());

        view.getInitialScene()
                .getLogInButton()
                .addActionListener(e -> switchLoginScene());
    }

    private void bindLoginSceneHandlers() {
        view.getLoginScene()
                .getSubmitButton()
                .addActionListener(e -> logIn());

        view.getCreateAccountScene()
                .getSubmitButton()
                .addActionListener(e -> createAccount());

    }

    public void logIn() {
        final String username = view.getLoginScene().getUsernameValue();
        final String password = view.getLoginScene().getPasswordValue();

        try {
            User user = model.getUserRepository().get(new User(username));
            model.setActiveUser(user);
            switchToVideoScene();

        }  catch (UserNotFoundException e) {
            wrongLoginRequest();
        } catch (IOException | SubscriptionTypeNotFound e) {
            serverConnectionError(3, e.getMessage(), view.getLoginScene());
        }
    }

    public void createAccount() {
        // TODO abstract below to .getUserCandidate()
        final String name = view.getCreateAccountScene().getNameValue();
        final String surname = view.getCreateAccountScene().getSurnameValue();
        final String email = view.getCreateAccountScene().getEmailValue();
        final String username = view.getCreateAccountScene().getUsernameValue();
        final String password = view.getCreateAccountScene().getPasswordValue();

        try {
            User newUser = model.getUserRepository().add(new User(username, password, name, surname, email));
            System.out.println(newUser);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void serverConnectionError(int seconds, String message, IFreezable scene) {
        scene.freezeScene();

        JOptionPane.showMessageDialog(null, "Server connection error: " + message, "Connection Error", JOptionPane.WARNING_MESSAGE);

        ActionListener listener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                scene.unfreezeScene();
            }
        };

        Timer timer = new Timer(seconds * 1000, listener);
        timer.setRepeats(false);
        timer.start();
    }

    public void wrongLoginRequest() {
        JOptionPane.showMessageDialog(null, "Username or password is wrong", "Connection Error", JOptionPane.WARNING_MESSAGE);
    }
}
