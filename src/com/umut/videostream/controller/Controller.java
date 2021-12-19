package com.umut.videostream.controller;

import com.umut.videostream.model.Movie;
import com.umut.videostream.model.User;
import com.umut.videostream.model.enums.EMovieGenre;
import com.umut.videostream.model.exceptions.MovieGenreNotFound;
import com.umut.videostream.model.exceptions.SubscriptionTypeNotFound;
import com.umut.videostream.model.exceptions.UserNotFoundException;
import com.umut.videostream.model.repository.tmdb.MovieTMDBRepository;
import com.umut.videostream.model.services.NetworkOperations;
import com.umut.videostream.view.CreateAccountScene;
import com.umut.videostream.view.IFreezable;
import com.umut.videostream.view.InitialScene;
import com.umut.videostream.view.View;
import com.umut.videostream.model.Model;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class Controller {
    private View view;
    private Model model;

    private MovieEventHandler movieEventHandler;
    private LoginEventHandler loginEventHandler;
    private CreateAccountEventHandler createAccountEventHandler;
    private InitialSceneEventHandler initialSceneEventHandler;

    public Controller(Model model, View view) {
        this.model = model;
        this.view = view;

        movieEventHandler = new MovieEventHandler();
        loginEventHandler = new LoginEventHandler();
        createAccountEventHandler = new CreateAccountEventHandler();
        initialSceneEventHandler = new InitialSceneEventHandler();

        bindEventHandlers();

        view.createInitialWindow();
    }

    /*
    Main functionality
     */

    public void logIn() {
        final String username = view.getLoginScene().getUsernameValue();
        final String password = view.getLoginScene().getPasswordValue();

        try {
            User user = model.getUserRepository().get(new User(username));
            model.setActiveUser(user);
            switchToVideoScene();

        } catch (UserNotFoundException e) {
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

    public void changeGenre() {
        view.getMovieScene().deleteMovies();

        EMovieGenre genre = (EMovieGenre) view.getMovieScene().getSelectGenreComboBox().getSelectedItem();
        System.out.println("Change: " + view.getMovieScene().getSelectGenreComboBox().getSelectedItem());

        loadMovies(genre);
    }


   /*
   State creators
    */

    private void loadInitialMovieState() {
        var userGenres = EMovieGenre.getGenreListBySubscriptionType(model.getActiveUser().getSubscriptionType());
        var randomGenre = EMovieGenre.getRandomGenre(userGenres);

        System.out.println(randomGenre);

        view.getMovieScene().renderComboBox(userGenres, randomGenre, movieEventHandler);

        loadMovies(randomGenre);
    }


    /*
    Event handler binders
     */

    private void bindEventHandlers() {
        bindInitialSceneHandlers();

        bindLoginSceneHandlers();
        bindCreateAccountSceneHandlers();

        bindMovieSceneHandlers();
    }


    private void bindInitialSceneHandlers() {
        view.getInitialScene()
                .getCreateAccountButton()
                .addActionListener(initialSceneEventHandler);

        view.getInitialScene()
                .getLogInButton()
                .addActionListener(initialSceneEventHandler);
    }

    private void bindLoginSceneHandlers() {
        view.getLoginScene()
                .getSubmitButton()
                .addActionListener(loginEventHandler);
    }

    private void bindCreateAccountSceneHandlers() {
        view.getCreateAccountScene()
                .getSubmitButton()
                .addActionListener(createAccountEventHandler);

    }
    private void bindMovieSceneHandlers() {
        view.getMovieScene()
                .getSelectGenreComboBox()
                .addActionListener(movieEventHandler);
    }


    /*
    Error related
     */
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


    /*
    Scene logic
     */

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


    /*
    Event handler classes
     */

    private class InitialSceneEventHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == view.getInitialScene().getLogInButton()) {
                switchLoginScene();
            } else if (e.getSource() == view.getInitialScene().getCreateAccountButton()) {
                switchToCreateAccountScene();
            }
        }
    }

    private class LoginEventHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == view.getLoginScene().getSubmitButton()) {
                logIn();
            }
        }
    }

    private class CreateAccountEventHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            createAccount();
        }
    }

    public class MovieEventHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == view.getMovieScene().getSelectGenreComboBox()) {
                changeGenre();
            }
        }
    }


}
