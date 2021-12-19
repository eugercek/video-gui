package com.umut.videostream.view;

public class View {
    private final InitialScene initialScene;
    private final CreateAccountScene createAccountScene;
    private final LoginScene loginScene;
    private final AdminScene adminScene;
    private final MovieScene videoScene;

    public View() {
        initialScene = new InitialScene();
        createAccountScene = new CreateAccountScene(initialScene);
        loginScene = new LoginScene(initialScene);
        adminScene = new AdminScene();
        videoScene = new MovieScene();
    }

    public void createInitialWindow() {
        // initialScene.setVisible(true);
        adminScene.setVisible(true);
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
        return videoScene;
    }
}
