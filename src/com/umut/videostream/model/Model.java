package com.umut.videostream.model;

import com.umut.videostream.model.repository.IMovieRepository;
import com.umut.videostream.model.repository.IUserRepository;
import com.umut.videostream.model.repository.UserJsonServerRepository;
import com.umut.videostream.model.repository.tmdb.MovieTMDBRepository;

public class Model {
    // Waits for dependency injection :)
    private IUserRepository userRepository;
    private IMovieRepository movieRepository;
    private User activeUser;

    public Model(String connectionString){
        userRepository = new UserJsonServerRepository();
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

    public User getActiveUser() {
        return activeUser;
    }

    public void setActiveUser(User activeUser) {
        this.activeUser = activeUser;
    }
}
