package com.lld.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Center {
    private final String id;
    private final String name;
    private final Map<Workout, Integer> workoutList;
    private final List<Timing> timings;
    private final List<Booking> bookings;

    public Center(String id, String name) {
        this.id = id;
        this.name = name;
        this.timings = new ArrayList<>();
        this.workoutList = new HashMap<>();
        this.bookings = new ArrayList<>();
    }

    public void addWorkoutDetails(WorkoutType workoutType, Integer startTime, Integer endTime, Integer capacity) {
        var timing = new Timing(startTime, endTime);
        for (var entry: workoutList.entrySet()) {
            var workout = entry.getKey();
            if (workout.getType() == workoutType) {
                if (!workout.isTimingAvailable(timing)) {
                    return;
                }
                workout.addTiming(timing);
                System.out.println(workoutType + " added from " + startTime + " to " + endTime + " with capacity: " + capacity);
                workoutList.put(workout, capacity);
            }
        }
    }

    public Map.Entry<Workout, Integer> getAvailableSlot(WorkoutType type) {
        for (var entry: workoutList.entrySet()) {
            var workout = entry.getKey();
            if (workout.getType() == type && entry.getValue() > 0) {
                return entry;
            }
        }

        return null;
    }

    public void addTiming(Timing timing) {
        timings.add(timing);
    }

    public void addActivity(WorkoutType workout) {
        workoutList.put(new Workout(workout), 0);
    }

    public String getName() {
        return name;
    }

    public boolean isBookingAvailable(WorkoutType type, Timing timing) {
        for (var entry: workoutList.entrySet()) {
            var workout = entry.getKey();
            if (workout.getType() == type) {
                if (!workout.isTimingAvailable(timing)) {
                     System.out.println("timing not available");
                     return false;
                } else if (entry.getValue() <= 0) {
                    System.out.println("slots not available");
                    return false;
                } else {
                    return true;
                }
            }
        }

        return false;
    }

    public synchronized void addBooking(User user, WorkoutType type, Timing timing) throws InterruptedException {
        for (var entry: workoutList.entrySet()) {
            var workout = entry.getKey();
            if (workout.getType() == type) {
                synchronized (workoutList) {
                    var booking = new Booking(this, workout, user, timing);
                    var currentSlots = workoutList.get(workout);
                    bookings.add(booking);
                    workoutList.put(workout, currentSlots-1);
                }
            }
        }

    }
    
    public synchronized void cancelSession(User user, WorkoutType type, Timing timing) throws InterruptedException {
        for (var entry: workoutList.entrySet()) {
            var workout = entry.getKey();
            if (workout.getType() == type) {
                synchronized (workoutList) {
                    var currentSlots = workoutList.get(workout);
                    cancelBooking(user, type, timing);
                    workoutList.put(workout, currentSlots+1);
                }
            }
        }

    }

    private void cancelBooking(User user, WorkoutType type, Timing timing) {
        for (var booking: bookings) {
            if (booking.getUser().getId().equals(user.getId()) && booking.getWorkout().getType() == type) {
                booking.cancelBooking();
            }
        }
    }







}
