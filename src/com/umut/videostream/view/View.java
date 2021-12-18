package com.umut.videostream.view;

public class View {
    private InitialScene initialScene;
    private CreateAccountScene createAccountScene;
    private LoginScene loginScene;
    private AdminScene adminScene;
    private MovieScene videoScene;

    public View() {
        initialScene = new InitialScene();
        createAccountScene = new CreateAccountScene(initialScene);
        loginScene = new LoginScene(initialScene);
        adminScene = new AdminScene();
        videoScene = new MovieScene();
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
        return videoScene;
    }
}
