package com.umut.videostream.model.repository;

import com.umut.videostream.model.User;
import com.umut.videostream.model.exceptions.UserNotFoundException;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/*
    You need to load database with `connectDatabase()`.
 */
public class TextFileDatabaseRepository implements IDatabaseRepository{
    private Scanner scanner;
    private String connectionString;

    public TextFileDatabaseRepository(String path){
        connectionString = parseConnectionString(path);
    }

    @Override
    public User get(User user) throws FileNotFoundException,UserNotFoundException {
        connectDatabase();
        while(scanner.hasNextLine()){
            String username = scanner.next(); // Username
            if(user.getUsername().equals(username)){
                String password = scanner.next(); // Password
                String name = scanner.next(); // Name
                String surname = scanner.next(); // Surname
                String email = scanner.next(); // Email
                return new User(username, password, name, surname, email);
            }
        }
        throw new UserNotFoundException(user);
    }

    @Override
    public void add(User user) throws FileNotFoundException {
        connectDatabase();
    }

    @Override
    public void update(User user) throws FileNotFoundException {
        connectDatabase();

    }

    @Override
    public void connectDatabase() throws FileNotFoundException {
        File file = new File(connectionString);
        scanner = new Scanner(file);
    }

    @Override
    /*
      No need to parse in this implementation.
     */
    public String parseConnectionString(String rawConnectionString) {
        return rawConnectionString;
    }
}
