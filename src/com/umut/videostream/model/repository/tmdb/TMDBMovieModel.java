package com.umut.videostream.model.repository.tmdb;

import com.google.gson.annotations.SerializedName;
import com.umut.videostream.model.enums.EMovieGenre;

public class TMDBMovieModel {
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

    private static EMovieGenre getGenreById(int id) {
        return switch (id) {
            case 28 -> EMovieGenre.ACTION;
            case 35 -> EMovieGenre.COMEDY;
            case 18 -> EMovieGenre.DRAMA;
            case 36 -> EMovieGenre.HISTORY;
            case 27 -> EMovieGenre.HORROR;
            case 10402 -> EMovieGenre.MUSIC;
            // TODO Find something to handle wrong ids
            default -> EMovieGenre.MUSIC;
        };
    }

    private static EMovieGenre[] getGenresById(int[] idList) {
        // TODO Please learn Java's map syntax
        EMovieGenre[] genres = new EMovieGenre[idList.length];
        for (int i = 0; i < idList.length; i++) {
            genres[i] = getGenreById(idList[i]);
        }
        return genres;
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

    public EMovieGenre[] getGenres() {
        return getGenresById(genreIds);
    }


    public boolean isAdult() {
        return adult;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getOverview() {
        return overview;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public boolean isHasVideo() {
        return hasVideo;
    }

    public int[] getGenreIds() {
        return genreIds;
    }
}
