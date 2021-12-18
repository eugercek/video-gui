package com.umut.videostream.model.repository.mockapi;

import com.umut.videostream.model.User;

public class MockAPIUserModel extends User {
    private String name;
    private String surname;
    private String username;
    private String password;
    private String subscriptionType;
    private int id;

    public MockAPIUserModel(String username, String password, String name, String surname, String email) {
        super(username, password, name, surname, email );
    }
}
