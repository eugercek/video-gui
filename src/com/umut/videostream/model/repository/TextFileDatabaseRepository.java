package com.umut.videostream.model.repository;
import com.umut.videostream.model.User;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class TextFileDatabaseRepository implements IDatabaseRepository{
    private Scanner scanner;
    private String connectionString;

    public TextFileDatabaseRepository(String path){
        connectionString = parseConnectionString(path);
        connectDatabase();

    }

    @Override
    public User get(User user) {
        return new User();
    }

    @Override
    public void add(User user) {
    }

    @Override
    public void update(User user) {

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
    public String parseConnectionString() {
        return connectionString;
    }


}
