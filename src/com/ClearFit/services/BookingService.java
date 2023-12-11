package com.lld.services;

import com.lld.models.Timing;
import com.lld.models.WorkoutType;

public class BookingService {

    private final CenterService centerService;
    private final UserService userService;

    public BookingService(CenterService centerService, UserService userService) {
        this.centerService = centerService;
        this.userService = userService;
    }

    public void bookSession(String name, String centerName, WorkoutType workoutType, Integer startTime, Integer endTime) {
        var user = userService.getUser(name);
        var timing = new Timing(startTime, endTime);
        var isAvailable = centerService.isWorkoutAvailable(centerName, workoutType, timing);
        if (!isAvailable) {
            System.out.println("not possible to book");
            return;
        }

        centerService.bookSession(user, centerName, workoutType, timing);
    }

    public void cancelBooking(String name, String centerName, WorkoutType workoutType, Integer startTime, Integer endTime) {
        var user = userService.getUser(name);
        var timing = new Timing(startTime, endTime);

        centerService.cancelSession(user, centerName, workoutType, timing);
    }
}

