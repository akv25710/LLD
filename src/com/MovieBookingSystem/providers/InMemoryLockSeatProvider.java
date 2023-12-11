package com.lld.MovieBookingSystem.providers;

import com.lld.MovieBookingSystem.models.Seat;
import com.lld.MovieBookingSystem.models.SeatLock;
import com.lld.MovieBookingSystem.models.Show;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryLockSeatProvider implements SeatLockProvider {
    private final Integer lockTimeout;
    private final Map<Show, Map<Seat, SeatLock>> locks;

    public InMemoryLockSeatProvider() {
        this.lockTimeout = 500;
        this.locks = new HashMap<>();
    }

    @Override
    synchronized public void lockSeats(Show show, List<Seat> seats, String user) {
        for (var seat: seats) {
            if (isSeatLocked(show, seat)) {
                throw new RuntimeException("seat is not available");
            }
        }

        for (var seat: seats) {
            if (isSeatLocked(show, seat)) {
                throw new RuntimeException("seat is not available");
            }
        }
    }

    @Override
    public void unlockSeats(Show show, List<Seat> seats, String user) {
        for (Seat seat: seats) {
            if (validateLocks(show, seat, user)) {
                unlockSeat(show, seat);
            }
        }
    }

    @Override
    public boolean validateLocks(Show show, Seat seat, String user) {
        return isSeatLocked(show, seat) && locks.get(show).get(seat).getLockedBy().equals(user);
    }

    @Override
    public List<Seat> getLockedSeats(Show show) {
        return null;
    }

    private void unlockSeat(Show show, Seat seat) {
        if (locks.containsKey(show)) {
            locks.get(show).remove(seat);
        }
    }

    private void lockSeat(Show show, Seat seat, String user) {
        if (!locks.containsKey(show)) {
            locks.put(show, new HashMap<>());
        }
        var seatLock = new SeatLock(seat, lockTimeout, user);
        locks.get(show).put(seat, seatLock);
    }

    private boolean isSeatLocked(final Show show, final Seat seat) {
        return locks.containsKey(show) && locks.get(show).containsKey(seat) && !locks.get(show).get(seat).isLockExpired();
    }
}
