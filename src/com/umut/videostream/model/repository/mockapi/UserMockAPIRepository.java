package com.umut.videostream.model.repository.mockapi;

import com.google.gson.Gson;
import com.umut.videostream.model.User;
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
    public User get(User user) throws UserNotFoundException, IOException {
        String json = NetworkOperations.downloadJsonString(getUserURLByUsername(user.getUsername()));
        User responseUser = gson.fromJson(json, User[].class)[0];

        // mockapi returns a user if there is no wanted user in db
        if(responseUser.getUsername().equals(user.getUsername())){
            return user;
        }
        throw new UserNotFoundException(responseUser);
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
}
