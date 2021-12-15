package com.umut.videostream.model.repository;

import com.umut.videostream.model.User;
import com.umut.videostream.model.exceptions.UserNotFoundException;

import java.io.IOException;

public interface IDatabaseRepository {
    User get(User user) throws UserNotFoundException,IOException;
    User add(User user) throws IOException;
    void connectDatabase() throws IOException;
    String parseConnectionString(String rawConnectionString) throws IOException;
}
