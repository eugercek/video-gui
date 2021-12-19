package com.umut.videostream.model.repository.mockapi;

import com.google.gson.Gson;
import com.umut.videostream.model.User;
import com.umut.videostream.model.exceptions.SubscriptionTypeNotFound;
import com.umut.videostream.model.exceptions.UserNotFoundException;
import com.umut.videostream.model.repository.IUserRepository;
import com.umut.videostream.model.services.NetworkOperations;

import java.io.IOException;
import java.text.MessageFormat;

/*
This class implements https://mockapi.io/projects/61bd22fcd8542f0017824b3a
 */
public class UserMockAPIRepository implements IUserRepository {
    private static String BASE_URL = "https://61bd22fcd8542f0017824b39.mockapi.io/api/v1/users?username=";
    Gson gson;

    public UserMockAPIRepository(){
        gson = new Gson();
    }
    @Override
    public User getUserByUsername(User user) throws UserNotFoundException, IOException, SubscriptionTypeNotFound {
        String json = NetworkOperations.downloadJsonString(getUserURLByUsername(user.getUsername()));
        MockAPIUserModel responseUser = gson.fromJson(json, MockAPIUserModel[].class)[0];
        User realUser = UserFactory.createUserFromMockAPIModel(responseUser);

        // mockapi returns a user if there is no wanted user in db
        if(responseUser.getUsername().equals(user.getUsername())){
            return realUser;
        }
        throw new UserNotFoundException(realUser);
    }

    @Override
    public User getUserById(int id) throws UserNotFoundException, IOException, SubscriptionTypeNotFound {
        String json = NetworkOperations.downloadJsonString(getUserURLById(id));

        MockAPIUserModel responseUser = gson.fromJson(json, MockAPIUserModel[].class)[0];
        User realUser = UserFactory.createUserFromMockAPIModel(responseUser);

        // mockapi returns a user if there is no wanted user in db
        if(responseUser.getId() == id){
            return realUser;
        }
        throw new UserNotFoundException(realUser);
    }

    // There is no way to POST custom JSON in mockapi
    @Override
    // TODO
    public User add(User user) throws IOException {
        String json = gson.toJson(user, User.class);
        return null;
    }

    @Override
    // TODO
    public User update(User user) throws IOException {
        return null;
    }

    public String getUserURLByUsername(String username){
        return MessageFormat.format("{0}?username={1}", BASE_URL, username);
    }

    public String getUserURLById(int id){
        return MessageFormat.format("{0}?id={1}", BASE_URL, id);
    }
}
