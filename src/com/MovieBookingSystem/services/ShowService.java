package com.lld.MovieBookingSystem.services;

import com.lld.MovieBookingSystem.models.Movie;
import com.lld.MovieBookingSystem.models.Screen;
import com.lld.MovieBookingSystem.models.Show;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ShowService {
    private final Map<String, Show> shows;


    public ShowService() {
        this.shows = new HashMap<>();
    }

    public Show getShow(String show) {
        return shows.get(show);
    }

    public Show createShow(Movie movie, Screen screen, Date startTime, Integer duration) {
        var id = UUID.randomUUID().toString();
        final Show show = new Show(id, movie, screen, startTime, duration);
        this.shows.put(id, show);
        return show;
    }

}
