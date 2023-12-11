package com.lld.MovieBookingSystem.providers;

import com.lld.MovieBookingSystem.models.Seat;
import com.lld.MovieBookingSystem.models.Show;

import java.util.List;

public interface SeatLockProvider {
    void lockSeats(Show show, List<Seat> seats, String user);
    void unlockSeats(Show show, List<Seat> seats, String user);
    boolean validateLocks(Show show, Seat seat, String user);
    List<Seat> getLockedSeats(Show show);
}
