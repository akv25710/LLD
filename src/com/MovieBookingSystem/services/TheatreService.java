package com.lld.MovieBookingSystem.services;

import com.lld.MovieBookingSystem.models.Screen;
import com.lld.MovieBookingSystem.models.Seat;
import com.lld.MovieBookingSystem.models.SeatType;
import com.lld.MovieBookingSystem.models.Theatre;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class TheatreService {
    private final Map<String, Theatre> theatres;
    private final Map<String, Screen> screens;
    private final Map<String, Seat> seats;

    public TheatreService() {
        this.theatres = new HashMap<>();
        this.screens = new HashMap<>();
        this.seats = new HashMap<>();
    }

    public Theatre getTheatre(final String theatreId) {
        return theatres.get(theatreId);
    }

    public Theatre createTheatre(String theatreName) {
        String theatreId = UUID.randomUUID().toString();
        Theatre theatre = new Theatre(theatreId, theatreName);
        theatres.put(theatreId, theatre);
        return theatre;
    }

    private Screen createScreen(final String screenName, final Theatre theatre) {
        String screenId = UUID.randomUUID().toString();
        Screen screen = new Screen(screenId, screenName, theatre);
        screens.put(screenId, screen);
        return screen;
    }

    public Screen createScreenInTheatre(String screenName,Theatre theatre) {
        Screen screen = createScreen(screenName, theatre);
        theatre.addScreen(screen);
        return screen;
    }

    public Seat createSeatInScreen(Integer rowNo, Integer seatNo, Screen screen, SeatType type) {
        String seatId = UUID.randomUUID().toString();
        Seat seat = new Seat(seatId, rowNo, seatNo, type);
        seats.put(seatId, seat);
        screen.addSeat(seat);

        return seat;
    }
}
