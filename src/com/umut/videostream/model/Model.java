package com.umut.videostream.model;

import com.umut.videostream.model.repository.TextFileDatabaseRepository;

public class Model {
    public TextFileDatabaseRepository db = new TextFileDatabaseRepository("./Secret/users.txt");
    public Model(){
    }

    public void logIn(String username, String password){

    }
}
