package com.umut.videostream.model;

import com.umut.videostream.model.enums.EMovieGenre;
import com.umut.videostream.model.enums.EMovieQuality;

public class Movie {
    private EMovieGenre[] genre;
    private EMovieQuality quality;
    private String contentPath;

    public Movie(EMovieGenre[] genre, EMovieQuality quality, String contentPath){
        this.genre = genre;
        this.quality = quality;
        this.contentPath = contentPath;

    }
}
