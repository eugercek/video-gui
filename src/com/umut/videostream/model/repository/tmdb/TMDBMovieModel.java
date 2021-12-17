package com.umut.videostream.model.repository.tmdb;

import com.google.gson.annotations.SerializedName;
import com.umut.videostream.model.Movie;
import com.umut.videostream.model.enums.EMovieGenre;
import com.umut.videostream.model.enums.EMovieQuality;

import java.util.Arrays;

public class TMDBMovieModel extends Movie{
    private boolean adult;
    private int id;
    private String title;
    private String overview;

    @SerializedName("poster_path")
    private String posterPath;

    @SerializedName("has_video")
    private boolean hasVideo;

    @SerializedName("genre_ids")
    private int[] genreIds;

    public TMDBMovieModel(boolean adult, int id, String title, String overview, String posterPath, String hasVideo, int[] genreIds) {
        // TODO Please learn Java's map syntax
        super(getGenresById(genreIds), EMovieQuality.Q_2K, posterPath);
    }

    @Override
    public String toString() {
        return "TMDBMovieModel{" +
                "adult=" + adult +
                ", id=" + id +
                ", title='" + title + '\'' +
                ", overview='" + overview + '\'' +
                ", posterPath='" + posterPath + '\'' +
                ", hasVideo=" + hasVideo +
                '}';
    }

    private static EMovieGenre getGenreById(int id){
        return switch (id){
            case 28 -> EMovieGenre.ACTION ;
            case 35 -> EMovieGenre.COMEDY;
            case 99 -> EMovieGenre.DOCUMENTARY;
            case 18 -> EMovieGenre.DRAMA;
            case 36 -> EMovieGenre.HISTORY;
            case 27 -> EMovieGenre.HORROR;
            case 10402 -> EMovieGenre.MUSIC;
            // TODO Find something to handle wrong ids
            default -> EMovieGenre.MUSIC;
        };
    }

    private static EMovieGenre[] getGenresById(int[] idList){
        EMovieGenre[] genres = new EMovieGenre[idList.length];
        for (int i = 0; i < idList.length; i++) {
            genres[i]  = getGenreById(idList[i]);
        }
        return genres;
    }
}
