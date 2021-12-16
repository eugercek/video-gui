package com.umut.videostream.model.enums;

public enum EMovieQuality {
    Q_480p,
    Q_720p,
    Q_2K;

    public EMovieQuality[] getQualityListBySubscriptionType(ESubscriptionType subscriptionType){
        return switch (subscriptionType){
            case FREE -> new EMovieQuality[]{Q_480p};
            case MEDIUM -> new EMovieQuality[]{Q_480p, Q_720p};
            case VIP -> new EMovieQuality[]{Q_480p,Q_720p,Q_2K};
        };
    }
}
