package com.umut.videostream.view;

public class View {
    private InitialScene initialScene = new InitialScene();
    private CreateAccountScene createAccountScene = new CreateAccountScene(initialScene);
    private LoginScene loginScene = new LoginScene(initialScene);
    private AdminScene adminScene = new AdminScene();

    public View() {
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
}
