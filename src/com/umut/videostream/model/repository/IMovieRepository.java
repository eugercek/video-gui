package com.umut.videostream.model.repository;

import com.umut.videostream.model.Movie;
import com.umut.videostream.model.enums.EMovieGenre;
import com.umut.videostream.model.enums.ESubscriptionType;

import java.io.IOException;

public interface IMovieRepository {
    Movie[] getMoviesByGenre(EMovieGenre genre) throws IOException;

    Movie[] getMoviesBySubscriptionType(ESubscriptionType subscriptionType);
}
