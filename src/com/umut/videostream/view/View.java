package com.umut.videostream.view;

import com.umut.videostream.model.enums.ESubscriptionType;

public class View {
    private final InitialScene initialScene;
    private final CreateAccountScene createAccountScene;
    private final LoginScene loginScene;
    private final AdminScene adminScene;
    private final MovieScene movieScene;

    private final WatchScene watchScene;

    public View() {
        initialScene = new InitialScene();
        createAccountScene = new CreateAccountScene(initialScene, ESubscriptionType.values());
        loginScene = new LoginScene(initialScene);
        adminScene = new AdminScene();
        movieScene = new MovieScene();
        watchScene = new WatchScene(movieScene);
    }

    public void createInitialWindow() {
        initialScene.setVisible(true);
    }

    public InitialScene getInitialScene() {
        return initialScene;
    }

    public CreateAccountScene getCreateAccountScene() {
        return createAccountScene;
    }

    public LoginScene getLoginScene() {
        return loginScene;
    }

    public AdminScene getAdminScene() {
        return adminScene;
    }

    public MovieScene getMovieScene() {
        return movieScene;
    }

    public WatchScene getWatchScene() {
        return watchScene;
    }
}
