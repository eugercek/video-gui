package com.umut.videostream.model;

import com.umut.videostream.model.repository.UserTextFileRepository;

public class Model {
    public UserTextFileRepository userRepository;
    public Model(String connectionString){
        userRepository = new UserTextFileRepository(connectionString);
    }

    public void logIn(String username, String password){

    }

    public UserTextFileRepository getUserRepository() {
        return userRepository;
    }

}
