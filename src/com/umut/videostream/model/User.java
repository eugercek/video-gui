package com.umut.videostream.model;

import com.umut.videostream.model.enums.ESubscriptionType;

public class User {
    int id;
    private String name;
    private String surname;
    private String username;
    private String password;
    private String email;
    private ESubscriptionType subscriptionType;

    public User(String username) {
        this.username = username;
        this.password = "default";
        this.name = "noname";
        this.surname = "nosurname";
        this.email = "noemail";
        this.id = -1;
    }

    public User(String username, String password, String name, String surname, String email, int id) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.id = id;
    }

    public static String[] getDataColumns() {
        return new String[]{
                "Id",
                "Name",
                "Username",
                "Password",
                "Email",
                "Subscription"
        };
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ESubscriptionType getSubscriptionType() {
        return subscriptionType;
    }

    public void setSubscriptionType(ESubscriptionType subscriptionType) {
        this.subscriptionType = subscriptionType;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", subscriptionType=" + subscriptionType +
                '}';
    }

    public String[] getData() {
        return new String[]{
                String.valueOf(id),
                name,
                username,
                password,
                email,
                subscriptionType.toString(),
        };
    }
}
