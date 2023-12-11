package com.lld.models;

public class Booking {
    private final Center center;
    private final Workout workout;
    private final User user;
    private final Timing timing;
    private BookingStatus status;

    public Booking(Center center, Workout workout, User user, Timing timing) {
        this.center = center;
        this.workout = workout;
        this.user = user;
        this.timing = timing;
        this.status = BookingStatus.IN_PROGRESS;
    }

    public void confirmBooking() {
        this.status = BookingStatus.BOOKED;
    }

    public void cancelBooking() {
        this.status = BookingStatus.CANCELLED;
    }

    public User getUser() {
        return user;
    }

    public Timing getTiming() {
        return timing;
    }

    public Workout getWorkout() {
        return workout;
    }

    public Center getCenter() {
        return center;
    }
}
