package com.umut.videostream.model.repository;

import com.umut.videostream.model.Movie;
import com.umut.videostream.model.enums.EMovieGenre;
import com.umut.videostream.model.enums.ESubscriptionType;

public class MovieLocalRepository implements IMovieRepository{
    @Override
    public Movie[] getMoviesByGenre(EMovieGenre genre) {
        return new Movie[0];
    }

    @Override
    public Movie[] getMoviesBySubscriptionType(ESubscriptionType subscriptionType) {
        return new Movie[0];
    }
}
