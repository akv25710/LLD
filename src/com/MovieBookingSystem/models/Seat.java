package com.lld.MovieBookingSystem.models;

public class Seat {

    private final String id;
    private final int row;
    private final int seatNo;
    private final SeatType type;
    private SeatStatus status;


    public Seat(String id, int row, int seatNo, SeatType type) {
        this.id = id;
        this.row = row;
        this.seatNo = seatNo;
        this.type = type;
        this.status = SeatStatus.AVAILABLE;
    }

    public boolean isBooked() {
        return status == SeatStatus.BOOKED;
    }

    public boolean isLocked() {
        return status == SeatStatus.LOCKED;
    }

    public boolean isAvailable() {
        return status == SeatStatus.AVAILABLE;
    }

    public void bookSeat() {
        status = SeatStatus.BOOKED;
    }

    public void lockSeat() {
        status = SeatStatus.LOCKED;
    }
}
