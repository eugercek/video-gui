package com.umut.videostream.model.repository.tmdb;

import com.google.gson.Gson;
import com.umut.videostream.model.Movie;
import com.umut.videostream.model.enums.EMovieGenre;
import com.umut.videostream.model.enums.ESubscriptionType;
import com.umut.videostream.model.repository.IMovieRepository;
import com.umut.videostream.model.services.NetworkOperations;

import java.io.IOException;
import java.text.MessageFormat;

public class MovieTMDBRepository implements IMovieRepository {
    private static final String API_KEY  = "c787ec79f5645b11b37480a74e0cc95b";
    private static final String IMG_BASE_URL  = "https://image.tmdb.org/t/p/";
    private final Gson gson;

    public MovieTMDBRepository(){
        gson = new Gson();
    }

    @Override
    public Movie[] getMoviesByGenre(EMovieGenre genre) throws IOException {
        String resp = NetworkOperations.downloadJsonString(getGenreUrl(genre));
        return gson.fromJson(resp, TMDBResponseModel.class).getMovies();
    }

    @Override
    public Movie[] getMoviesBySubscriptionType(ESubscriptionType subscriptionType) {
        return new Movie[0];
    }

    // https://image.tmdb.org/t/p/w500/7ajHGIAYNMiIzejy1LJWdPrcAx8.jpg
    private String getPosterURL(int width, String posterPath){
        return MessageFormat.format("{0}w{1}/{2}",IMG_BASE_URL ,width, posterPath);
    }

    // http://api.themoviedb.org/3/discover/movie?api_key={{key}}&with_genres=35
    private String getGenreUrl(EMovieGenre genre){
        return MessageFormat.format("https://api.themoviedb.org/3/discover/movie?api_key={0}&with_genres={1}",
                API_KEY,
                getGenreId(genre)
                );
    }

    // TODO Should bake this into Enum
    // Problem of baking is can't extend enum, also can't hard code values in enum for obvious reasons
    // Implementation of API's id values
    // https://api.themoviedb.org/3/genre/movie/list?api_key={{key}}
    private int getGenreId(EMovieGenre genre){
        return switch (genre){
            case ACTION -> 28;
            case COMEDY -> 35;
            case DOCUMENTARY -> 99;
            case DRAMA -> 18;
            case HISTORY -> 36;
            case HORROR -> 27;
            case MUSIC -> 10402;
        };
    }
    private EMovieGenre getGenreById(int id){
        return switch (id){
            case 28 -> EMovieGenre.ACTION ;
            case 35 -> EMovieGenre.COMEDY;
            case 99 -> EMovieGenre.DOCUMENTARY;
            case 18 -> EMovieGenre.DRAMA;
            case 36 -> EMovieGenre.HISTORY;
            case 27 -> EMovieGenre.HORROR;
            case 10402 -> EMovieGenre.MUSIC;
            default -> EMovieGenre.MUSIC;
        };
    }
}