package com.umut.videostream.model.exceptions;

import java.io.IOException;

public class MovieGenreNotFound extends IOException {
    public MovieGenreNotFound(String string) {
        super("Genre " + string  + " not found");
    }

}
