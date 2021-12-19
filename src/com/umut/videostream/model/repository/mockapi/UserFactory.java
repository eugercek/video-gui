package com.umut.videostream.model.repository.mockapi;

import com.umut.videostream.model.User;
import com.umut.videostream.model.enums.ESubscriptionType;
import com.umut.videostream.model.exceptions.SubscriptionTypeNotFound;

public class UserFactory {
    private UserFactory() {
    }

    public static User createUserFromMockAPIModel(MockAPIUserModel oldUser) throws SubscriptionTypeNotFound {
        User user = new User(
                oldUser.getUsername(),
                oldUser.getPassword(),
                oldUser.getName(),
                oldUser.getSurname(),
                oldUser.getEmail(),
                oldUser.getId()
        );

        user.setSubscriptionType(ESubscriptionType.createSubscriptionTypeFromString(oldUser.getSubscriptionType()));

        return user;
    }
}
