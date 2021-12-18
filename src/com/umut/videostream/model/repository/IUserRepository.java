package com.umut.videostream.model.repository;

import com.umut.videostream.model.User;
import com.umut.videostream.model.exceptions.UserNotFoundException;

import java.io.IOException;

public interface IUserRepository {
    User get(User user) throws UserNotFoundException,IOException;
    User add(User user) throws IOException;
    User update(User user) throws IOException;
}
