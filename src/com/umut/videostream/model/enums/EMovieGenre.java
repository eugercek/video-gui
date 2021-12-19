package com.umut.videostream.model.enums;

import com.umut.videostream.model.exceptions.MovieGenreNotFound;

import java.util.Random;

public enum EMovieGenre {
    ACTION,
    COMEDY,
    DRAMA,
    HISTORY,
    HORROR,
    MUSIC;

    public static EMovieGenre[] getGenreListBySubscriptionType(ESubscriptionType subscriptionType) {
        return switch (subscriptionType) {
            case FREE -> new EMovieGenre[]{
                    ACTION,
                    COMEDY,
                    DRAMA,
            };
            case MEDIUM -> new EMovieGenre[]{
                    ACTION,
                    COMEDY,
                    DRAMA,
                    HORROR,

            };
            case VIP -> new EMovieGenre[]{
                    ACTION,
                    COMEDY,
                    DRAMA,
                    HISTORY,
                    HORROR,
                    MUSIC
            };
        };
    }

    public static EMovieGenre getRandomGenre() {
        Random generator = new Random();
        int index = generator.nextInt(EMovieGenre.values().length);

        return EMovieGenre.values()[index];
    }

    public static EMovieGenre getRandomGenre(EMovieGenre[] genres) {
        Random generator = new Random();
        int index = generator.nextInt(genres.length);

        return genres[index];
    }

    public static EMovieGenre getRandomGenreForSubscriptionType(ESubscriptionType subscriptionType) {
        Random generator = new Random();
        EMovieGenre[] genres = getGenreListBySubscriptionType(subscriptionType);
        int index = generator.nextInt(genres.length);

        return genres[index];
    }

    public static EMovieGenre createGenreFromOrdinal(String string) throws MovieGenreNotFound {
        for (EMovieGenre genre : EMovieGenre.values()) {
            if (string.equals(genre.ordinal())) {
                return genre;
            }
        }
        throw new MovieGenreNotFound(string);
    }
}
