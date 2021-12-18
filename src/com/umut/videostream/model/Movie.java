package com.umut.videostream.model;

import com.umut.videostream.model.enums.EMovieGenre;
import com.umut.videostream.model.enums.EMovieQuality;

public class Movie {
    private EMovieGenre[] genres;
    private EMovieQuality[] qualities;
    private String contentPath;
    private String title;

    public Movie(EMovieGenre[] genre, EMovieQuality[] qualities, String contentPath, String title){
        this.genres = genre;
        this.qualities = qualities;
        this.contentPath = contentPath;
        this.title = title;

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
