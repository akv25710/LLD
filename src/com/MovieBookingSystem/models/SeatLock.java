package com.lld.MovieBookingSystem.models;

import java.util.Date;

public class SeatLock {
    private Seat seat;
    private Integer timeout; // in seconds
    private Date lockTime;
    private String lockedBy;

    public SeatLock(Seat seat, Integer timeout, String user) {
        this.seat = seat;
        this.lockedBy = user;
        this.lockTime = new Date();
        this.timeout = timeout;
    }

    public String getLockedBy() {
        return lockedBy;
    }

    public boolean isLockExpired() {
        var lockExpireTime = lockTime.toInstant().plusSeconds(timeout);
        var currentTime = new Date().toInstant();
        return currentTime.isAfter(lockExpireTime);
    }
}
