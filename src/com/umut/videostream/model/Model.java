package com.umut.videostream.model;

import com.umut.videostream.model.repository.IMovieRepository;
import com.umut.videostream.model.repository.IUserRepository;
import com.umut.videostream.model.repository.mockapi.UserMockAPIRepository;
import com.umut.videostream.model.repository.tmdb.MovieTMDBRepository;

public class Model {
    // Waits for dependency injection :)
    public IUserRepository userRepository;
    public IMovieRepository movieRepository;

    public Model(String connectionString){
        userRepository = new UserMockAPIRepository();
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
