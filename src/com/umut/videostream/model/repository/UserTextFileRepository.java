package com.umut.videostream.model.repository;

import com.umut.videostream.model.User;
import com.umut.videostream.model.exceptions.UserNotFoundException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/*
    You need to load database with `connectDatabase()`.
 */
public class UserTextFileRepository implements IUserRepository {
    private Scanner scanner;
    private String connectionString;

    public UserTextFileRepository(String path){
        connectionString = parseConnectionString(path);
    }

    @Override
    public User get(User user) throws FileNotFoundException,UserNotFoundException {
        connectDatabase();
        while(scanner.hasNextLine()){
            String username = scanner.next();
            if(user.getUsername().equals(username)){
                String password = scanner.next();
                String name = scanner.next();
                String surname = scanner.next();
                String email = scanner.next();
                return new User(username, password, name, surname, email);
            }
        }
        throw new UserNotFoundException(user);
    }

    @Override
    // TODO Add unique username check
    public User add(User user) throws IOException {
        FileWriter writer = new FileWriter(connectionString);
        writer.append(formatUserForWrite(user));
        writer.close();
        return user;
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

    private String formatUserForWrite(User user){
        String[] values = {user.getUsername(), user.getPassword(), user.getName(), user.getSurname(), user.getEmail()};
        return String.join(" ", values);
    }
}
