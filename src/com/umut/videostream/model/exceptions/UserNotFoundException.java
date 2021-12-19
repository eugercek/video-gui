package com.umut.videostream.model.exceptions;

import com.umut.videostream.model.User;

import java.io.IOException;

public class UserNotFoundException extends IOException {
    public UserNotFoundException() {
        super("User not found");
    }

    public UserNotFoundException(User user) {
        super("User " + user.getUsername() + " not found");
    }
}
