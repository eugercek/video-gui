package com.umut.videostream.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class View {
    private InitialScene initialScene = new InitialScene();
    private CreateAccountScene createAccountScene = new CreateAccountScene(initialScene);
    private LoginScene loginScene = new LoginScene(initialScene);

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


}
