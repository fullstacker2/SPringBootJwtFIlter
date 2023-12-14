package com.example.springbootjwt.Model;

public class PayloadValidation {
    public static boolean payloadVal(Movie movie) {
        if(movie.getId() != null) {
            return false;
        }
        return true;
    }
}
