package com.umut.videostream.model.repository;

import com.umut.videostream.model.User;

public interface IDatabaseRepository {
    void get(User user);
    void add(User user);
    void update(User user);
}
