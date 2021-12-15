package com.umut.videostream.model;

import com.umut.videostream.model.repository.TextFileDatabaseRepository;

import java.io.FileNotFoundException;

public class Model {
    public TextFileDatabaseRepository repo;
    public Model(String connectionString){
        repo = new TextFileDatabaseRepository(connectionString);
    }

    public void logIn(String username, String password){

    }

    public TextFileDatabaseRepository getRepo() {
        return repo;
    }

}
