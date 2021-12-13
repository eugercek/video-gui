package com.umut.videostream.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class View {
    private final InitialScene initialScene = new InitialScene();

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
            if(event.getSource() == initialScene.logInButton){
                System.out.println("Login");
            }
            else if (event.getSource() == initialScene.createAccountButton){
                System.out.println("Create Account");
            }
        }
    }

}
