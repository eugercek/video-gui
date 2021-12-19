package com.umut.videostream.model.repository.tmdb;

import com.umut.videostream.model.Movie;
import com.umut.videostream.model.enums.EMovieGenre;
import com.umut.videostream.model.enums.EMovieQuality;

public class MovieFactory {
    private MovieFactory() {
    }

    public static Movie creatMovieFromTMDBMovieModel(TMDBMovieModel tmdb) {
        String title = tmdb.getTitle();
        EMovieQuality[] qualities = {EMovieQuality.Q_2K}; // TODO
        EMovieGenre[] genres = tmdb.getGenres();
        String contentPath = tmdb.getPosterPath();
        boolean adult = tmdb.isAdult();

        return new Movie(genres, qualities, contentPath, title, adult);
    }
}
