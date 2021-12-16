package com.umut.videostream.model.enums;

public enum EMovieGenre {
    ACTION,
    COMEDY,
    DOCUMENTARY,
    DRAMA,
    HISTORY,
    HORROR,
    MUSIC;

    public EMovieGenre[] getGenreListBySubscriptionType(ESubscriptionType subscriptionType){
        return switch (subscriptionType){
            case FREE -> new EMovieGenre[]{
                    ACTION,
                    COMEDY,
                    DRAMA,
            };
            case MEDIUM -> new EMovieGenre[]{
                    ACTION,
                    COMEDY,
                    DRAMA,
                    HISTORY,
                    HORROR,

            };
            case VIP -> new EMovieGenre[]{
                    ACTION,
                    COMEDY,
                    DOCUMENTARY,
                    DRAMA,
                    HISTORY,
                    HORROR,
                    MUSIC
            };
        };
    }

}
