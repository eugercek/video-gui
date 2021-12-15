package com.umut.videostream.model.repository;

import com.umut.videostream.model.User;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface IDatabaseRepository {
    User get(User user);
    void add(User user);
    void update(User user);
    void connectDatabase() throws IOException;
    String parseConnectionString(String rawConnectionString) throws IOException;
}
