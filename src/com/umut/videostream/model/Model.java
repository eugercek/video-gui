package com.umut.videostream.model;

import com.umut.videostream.model.enums.EMovieGenre;
import com.umut.videostream.model.repository.IMovieRepository;
import com.umut.videostream.model.repository.IUserRepository;
import com.umut.videostream.model.repository.UserTextFileRepository;
import com.umut.videostream.model.repository.tmdb.MovieTMDBRepository;

import java.io.IOException;

public class Model {
    // Waits for dependency injection :)
    public IUserRepository userRepository;
    public IMovieRepository movieRepository;

    public Model(String connectionString){
        userRepository = new UserTextFileRepository(connectionString);
        movieRepository = new MovieTMDBRepository();
    }

    public void logIn(String username, String password){

    }

    public IUserRepository getUserRepository() {
        return userRepository;
    }

    public IMovieRepository getMovieRepository() {
        return movieRepository;
    }

}
