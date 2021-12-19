package com.umut.videostream.model.repository.tmdb;

import com.google.gson.annotations.SerializedName;

import java.util.Arrays;

// Class for Gson
public class TMDBResponseModel {
    private int page;
    @SerializedName("results")
    private TMDBMovieModel[] movies;
    @SerializedName("total_pages")
    private int totalPages;
    @SerializedName("total_results")
    private int totalResults;

    public int getPage() {
        return page;
    }

    public TMDBMovieModel[] getMovies() {
        return movies;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public int getTotalResults() {
        return totalResults;
    }

    @Override
    public String toString() {
        return "TMDBResponseModel{" +
                "page=" + page +
                ", movies=" + Arrays.toString(movies) +
                ", totalPages=" + totalPages +
                ", totalResults=" + totalResults +
                '}';
    }
}
