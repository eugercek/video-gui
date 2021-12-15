package com.umut.videostream.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class View {
    private InitialScene initialScene = new InitialScene();
    private CreateAccountScene createAccountScene = new CreateAccountScene(initialScene);
    private LoginScene loginScene = new LoginScene(initialScene);

    public View() {
        initialScene.addLogInListener(new InitialListener());
        initialScene.addCreatAccountListener(new InitialListener());
    }

    public void createInitialWindow() {
        initialScene.setVisible(true);
    }

    // There is no logic in InitialScene that's why there is no need for Controller or Model
    class InitialListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            if (event.getSource() == initialScene.getLogInButton()) {
                initialScene.setVisible(false);
                loginScene.setVisible(true);
            } else if (event.getSource() == initialScene.getCreateAccountButton()) {
                initialScene.setVisible(false);
                createAccountScene.setVisible(true);
            }
        }
    }

}
