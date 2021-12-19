package com.umut.videostream.controller;

import com.umut.videostream.model.Model;
import com.umut.videostream.model.Movie;
import com.umut.videostream.model.User;
import com.umut.videostream.model.enums.EMovieGenre;
import com.umut.videostream.model.enums.EMovieQuality;
import com.umut.videostream.model.enums.ESubscriptionType;
import com.umut.videostream.model.exceptions.SubscriptionTypeNotFound;
import com.umut.videostream.model.exceptions.UserNotFoundException;
import com.umut.videostream.model.repository.tmdb.MovieTMDBRepository;
import com.umut.videostream.model.services.NetworkOperations;
import com.umut.videostream.view.IFreezable;
import com.umut.videostream.view.View;
import com.umut.videostream.view.WatchScene;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.Arrays;

public class Controller {
    private final View view;
    private final Model model;

    private final MovieEventHandler movieEventHandler;
    private final LoginEventHandler loginEventHandler;
    private final CreateAccountEventHandler createAccountEventHandler;
    private final InitialSceneEventHandler initialSceneEventHandler;
    private final  WatchEventHandler watchEventHandler;
    private final  MovieMouseAdapter movieMouseAdapter;

    public Controller(Model model, View view) {
        this.model = model;
        this.view = view;

        movieEventHandler = new MovieEventHandler();
        loginEventHandler = new LoginEventHandler();
        createAccountEventHandler = new CreateAccountEventHandler();
        initialSceneEventHandler = new InitialSceneEventHandler();
        watchEventHandler = new WatchEventHandler();
        movieMouseAdapter = new MovieMouseAdapter();

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
            User user = model.getUserRepository().getUserByUsername(new User(username));
            model.setActiveUser(user);

            if (isUserAdmin(user)) {
                switchToAdminScene();
            } else {
                switchToVideoScene();
            }

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
            User newUser = model.getUserRepository().add(new User(username, password, name, surname, email, -1));
            model.getUserRepository().add(newUser);
            view.getCreateAccountScene().setVisible(false);
            view.getInitialScene().setVisible(true);
        } catch (IOException e) {
            serverConnectionError(1, e.getMessage(), view.getCreateAccountScene());
            e.printStackTrace();
        }
    }

    private void loadMovies(EMovieGenre genre) {
        try {
            Movie[] movies = model.getMovieRepository().getMoviesByGenre(genre);
            for (Movie movie : movies) {
                Image image = NetworkOperations.downloadImage(MovieTMDBRepository.getPosterURL(300, movie.getContentPath()));
                view.getMovieScene().renderMovie("Title", image, movieMouseAdapter);
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

    private void generateReport() {
        if (view.getAdminScene().isIdMode()) {
            User user;
            int id = Integer.parseInt(view.getAdminScene().getUserNameTextField().getText());
            try {
                user = model.getUserRepository().getUserById(id);
                view.getAdminScene().renderTable(new Object[][]{user.getData()}, User.getDataColumns());
                System.out.println(user);
            } catch (UserNotFoundException | SubscriptionTypeNotFound e) {
                JOptionPane.showInputDialog(e.getMessage());
            } catch (IOException e) {
                serverConnectionError(1, e.getMessage(), view.getAdminScene());
            }
        } else {
            ESubscriptionType subscriptionType = null;
            try {
                // TODO Fix this ugliness
                subscriptionType = ESubscriptionType.createSubscriptionTypeFromString(view.getAdminScene().getSubscriptionTypeComboBox().getSelectedItem().toString());
            } catch (SubscriptionTypeNotFound e) {
                e.printStackTrace();
            }
            User[] users;

            try {
                users = model.getUserRepository().getAllUsersBySubscriptionType(subscriptionType);
                Object[][] data = new Object[users.length][];

                for (int i = 0; i < users.length; i++) {
                    System.out.println(Arrays.toString(users[i].getData()));
                    data[i] = users[i].getData();
                }

                view.getAdminScene().renderTable(data, User.getDataColumns());
            } catch (IOException e) {
                serverConnectionError(1, e.getMessage(), view.getAdminScene());
            } catch (SubscriptionTypeNotFound e) {
                JOptionPane.showInputDialog(e.getMessage());
            }
        }
    }

    private void changeQuality() {
        EMovieQuality quality = (EMovieQuality) view.getWatchScene().getQualityComboBox().getSelectedItem();

        String message;

        switch (quality){
            case Q_2K -> message = "Watching movie in 2K :)";
            case Q_720p-> message = "Watching movie in 720p";
            default -> message = WatchScene.FIRST_MESSAGE;
        }

        view.getWatchScene().getLabel().setText(message);
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

    private void loadInitialAdminState() {
        var scene = view.getAdminScene();

        // At first Search by user id is usable
        scene.getSubscriptionTypeComboBox().setVisible(false);
    }

    private void loadInitialWatchState() {
        ESubscriptionType type = model.getActiveUser().getSubscriptionType();
        EMovieQuality[] qualities = EMovieQuality.getQualityListBySubscriptionType(type);
        view.getWatchScene().setQualityList(qualities, watchEventHandler);
    }



    /*
    Event handler binders
     */

    // I would use lambda expressions but assignment instructions dictates below
    private void bindEventHandlers() {
        bindInitialSceneHandlers();

        bindLoginSceneHandlers();
        bindCreateAccountSceneHandlers();

        bindMovieSceneHandlers();

        bindWatchSceneHandlers();


        bindAdminSceneHandlers();
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

    private void bindAdminSceneHandlers() {
        var adminScene = view.getAdminScene();

        adminScene.getSubmitButton()
                .addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        generateReport();
                    }
                });

        var typeComboBox = adminScene.getTypeComboBox();

        typeComboBox
                .addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (typeComboBox.getSelectedItem().toString().equals("User ID")) {
                            adminScene.getUserNameTextField().setVisible(true);
                            adminScene.getSubscriptionTypeComboBox().setVisible(false);
                        } else {
                            adminScene.getUserNameTextField().setVisible(false);
                            adminScene.getSubscriptionTypeComboBox().setVisible(true);

                        }
                    }
                });
    }

    private void bindWatchSceneHandlers(){
        view.getWatchScene()
                .getQualityComboBox()
                .addActionListener(watchEventHandler);
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

    private void switchToAdminScene() {
        view.getLoginScene().setVisible(false);
        view.getAdminScene().setVisible(true);

        loadInitialAdminState();
    }

    private void switchToWatchScene(){
        view.getMovieScene().setVisible(false);
        view.getWatchScene().setVisible(true);

        loadInitialWatchState();
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

    public class MovieMouseAdapter extends MouseAdapter{
        @Override
        public void mouseClicked(MouseEvent e) {
            switchToWatchScene();
        }
    }

    public class WatchEventHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            changeQuality();
        }
    }

    /*
    Util
     */

    private boolean isUserAdmin(User user) {
        return user.getUsername().equals("admin");
    }

}
