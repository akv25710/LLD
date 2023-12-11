package com.lld.MovieBookingSystem.models;

import java.util.Date;

public class Show {
    private final String id;
    private final Movie movie;
    private final Screen screen;
    private final Date startTime;
    private final Integer duration; // in seconds


    public Show(String id, Movie movie, Screen screen, Date startTime, Integer duration) {
        this.id = id;
        this.movie = movie;
        this.screen = screen;
        this.startTime = startTime;
        this.duration = duration;
    }
}
