package com.umut.videostream.model.repository;

import com.umut.videostream.model.User;
import com.umut.videostream.model.enums.ESubscriptionType;
import com.umut.videostream.model.exceptions.SubscriptionTypeNotFound;
import com.umut.videostream.model.exceptions.UserNotFoundException;

import java.io.IOException;

public interface IUserRepository {
    User getUserByUsername(User user) throws IOException, SubscriptionTypeNotFound;

    User getUserById(int id) throws IOException, SubscriptionTypeNotFound;

    User[] getAllUsersBySubscriptionType(ESubscriptionType subscriptionType) throws IOException, SubscriptionTypeNotFound;

    User add(User user) throws IOException;

    User update(User user) throws IOException;
}
