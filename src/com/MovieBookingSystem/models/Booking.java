package com.lld.MovieBookingSystem.models;

import java.util.List;
import java.util.stream.Collectors;

public class Booking {
    private final String id;
    private final Show show;
    private final List<Seat> seats;
    private final String user;

    private BookingStatus status;

    public Booking(String id, Show show, List<Seat> seats, String user) {
        this.id = id;
        this.show = show;
        this.seats = seats;
        this.user = user;
        this.status = BookingStatus.CREATED;
    }

    public Show getShow() {
        return show;
    }

    public String getUser() {
        return user;
    }

    public List<Seat> getSeatsBooked() {
        return seats.stream().filter(Seat::isBooked).collect(Collectors.toList());
    }

    public void confirmBooking() {
        if (status == BookingStatus.CONFIRMED) {
            throw new IllegalStateException("Booking already confirmed");
        }

        status = BookingStatus.CONFIRMED;
    }

    public void expireBooking() {
        if (status == BookingStatus.CONFIRMED) {
            throw new IllegalStateException("Cannot expire confirmed booking");
        }

        status = BookingStatus.EXPIRED;
    }

    public boolean isConfirmed() {
        return status == BookingStatus.CONFIRMED;
    }
}
