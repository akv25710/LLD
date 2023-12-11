package com.lld.MovieBookingSystem.services;

import com.lld.MovieBookingSystem.models.Movie;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class MovieService {

    private final Map<String , Movie> movies;

    public MovieService() {
        this.movies = new HashMap<>();
    }

    public Movie getMovie(String movie) {
        if (!movies.containsKey(movie)) {
            throw new IllegalArgumentException("movie invalid");
        }
        return movies.get(movie);
    }

    public Movie createMovie(String name) {
        var id = UUID.randomUUID().toString();
        var movie = new Movie(id, name);
        movies.put(id, movie);
        return movie;
    }
}
