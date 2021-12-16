package com.umut.videostream.model.repository;

import com.umut.videostream.model.Movie;
import com.umut.videostream.model.enums.EMovieGenre;
import com.umut.videostream.model.enums.ESubscriptionType;

import java.text.MessageFormat;

public class MovieTMDBRepository implements IMovieRepository{
    private static final String API_KEY  = "c787ec79f5645b11b37480a74e0cc95b";
    private static final String IMG_BASE_URL  = "https://image.tmdb.org/t/p/";

    @Override
    public Movie[] getMoviesByGenre(EMovieGenre genre) {
        return new Movie[0];
    }

    @Override
    public Movie[] getMoviesBySubscriptionType(ESubscriptionType subscriptionType) {
        return new Movie[0];
    }

    // https://image.tmdb.org/t/p/w500/bXAVveHiLotZbWdg3PKGhAzxYKP.jpg
    private String getPosterURL(int width, String posterPath){
        return MessageFormat.format("{0}w{1}/{2}",IMG_BASE_URL ,width, posterPath);
    }

}
