package com.umut.videostream.model.repository;

import com.umut.videostream.model.User;
import com.umut.videostream.model.exceptions.UserNotFoundException;

import java.io.IOException;

public interface IDatabaseRepository {
    User get(User user) throws UserNotFoundException,IOException;
    void add(User user) throws UserNotFoundException, IOException;
    void update(User user) throws  UserNotFoundException, IOException;
    void connectDatabase() throws UserNotFoundException, IOException;
    String parseConnectionString(String rawConnectionString) throws IOException;
}
