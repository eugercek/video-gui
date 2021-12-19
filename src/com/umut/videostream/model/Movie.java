package com.umut.videostream.model;

import com.umut.videostream.model.enums.EMovieGenre;
import com.umut.videostream.model.enums.EMovieQuality;

public class Movie {
    private final EMovieGenre[] genres;
    private final EMovieQuality[] qualities;
    private final String contentPath;
    private final String title;
    private final boolean adult;

    public Movie(EMovieGenre[] genre, EMovieQuality[] qualities, String contentPath, String title, boolean adult) {
        this.genres = genre;
        this.qualities = qualities;
        this.contentPath = contentPath;
        this.title = title;
        this.adult = adult;

    }

    public EMovieGenre[] getGenres() {
        return genres;
    }

    public EMovieQuality[] getQualities() {
        return qualities;
    }

    public String getContentPath() {
        return contentPath;
    }
}
