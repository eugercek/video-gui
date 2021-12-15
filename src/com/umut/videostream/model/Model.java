package com.umut.videostream.model;

import com.umut.videostream.model.repository.TextFileDatabaseRepository;

public class Model {
    public TextFileDatabaseRepository getRepo() {
        return repo;
    }

    public TextFileDatabaseRepository repo;
    public Model(String connectionString){
        repo = new TextFileDatabaseRepository(connectionString);
    }

    public void logIn(String username, String password){

    }
}
