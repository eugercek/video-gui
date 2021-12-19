package com.umut.videostream.model.repository;

import com.google.gson.Gson;
import com.sun.source.doctree.SummaryTree;
import com.umut.videostream.model.User;
import com.umut.videostream.model.enums.ESubscriptionType;
import com.umut.videostream.model.exceptions.SubscriptionTypeNotFound;
import com.umut.videostream.model.exceptions.UserNotFoundException;
import com.umut.videostream.model.repository.mockapi.MockAPIUserModel;
import com.umut.videostream.model.repository.mockapi.UserFactory;
import com.umut.videostream.model.services.NetworkOperations;
import jdk.javadoc.doclet.Reporter;

import javax.management.relation.Relation;
import java.io.IOException;
import java.text.MessageFormat;

public class UserJsonServerRepository implements IUserRepository {
    private static String BASE_URL = "http://localhost:3000/users";
    Gson gson;

    public UserJsonServerRepository() {
        gson = new Gson();
    }

    @Override
    public User getUserByUsername(User user) throws UserNotFoundException, IOException, SubscriptionTypeNotFound {
        String json = NetworkOperations.downloadJsonString(getUserURLByUsername(user.getUsername()));

        MockAPIUserModel responseUser = gson.fromJson(json, MockAPIUserModel[].class)[0];
        User realUser = UserFactory.createUserFromMockAPIModel(responseUser);

        return realUser;
    }


    @Override
    public User getUserById(int id) throws UserNotFoundException, IOException, SubscriptionTypeNotFound {
        String json = NetworkOperations.downloadJsonString(getUserURLById(id));

        MockAPIUserModel responseUser = gson.fromJson(json, MockAPIUserModel[].class)[0];
        User realUser = UserFactory.createUserFromMockAPIModel(responseUser);

        return realUser;
    }

    @Override
    public User[] getAllUsersBySubscriptionType(ESubscriptionType subscriptionType) throws IOException, SubscriptionTypeNotFound {
        String json = NetworkOperations.downloadJsonString(getSubscriptionTypeURL(subscriptionType));

        MockAPIUserModel[] responseUsers = gson.fromJson(json, MockAPIUserModel[].class);
        User[] realUsers = new User[responseUsers.length];

        for(int i = 0 ; i < responseUsers.length; i++){
            realUsers[i]  = UserFactory.createUserFromMockAPIModel(responseUsers[i]);
        }

        return realUsers;
    }

    @Override
    public User add(User user) throws IOException {
        String json = gson.toJson(user, User.class);
        NetworkOperations.postJson(getAddUserURLByUsername(user.getUsername()), json);
        return user;
    }

    @Override
    public User update(User user) throws IOException {
        return null;
    }

    private String getUserURLById(int id) {
        return MessageFormat.format("{0}?id={1}", BASE_URL, id);
    }

    private String getUserURLByUsername(String username) {
        return MessageFormat.format("{0}?username={1}", BASE_URL, username);
    }

    private String getAddUserURLByUsername(String username) {
        return MessageFormat.format("{0}?username={1}", BASE_URL, username);
    }

    private String getSubscriptionTypeURL(ESubscriptionType subscriptionType) {
        System.out.println(subscriptionType);
        return MessageFormat.format("{0}?subscriptionType={1}", BASE_URL, subscriptionType.name());
    }


}
