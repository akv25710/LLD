package com.lld;

import com.lld.models.Timing;
import com.lld.models.WorkoutType;
import com.lld.services.BookingService;
import com.lld.services.CenterService;
import com.lld.services.UserService;

import java.util.List;

public class Main {

    public static void main(String[] args) {
	// write your code here
        var centerService = new CenterService();

        var center1 = centerService.addCenter("Koramangala");
        var timing1 = new Timing(6, 9);
        var timing2 = new Timing(18, 21);
        centerService.addCenterTimings("Koramangala", List.of(timing1, timing2));
        centerService.addCenterActivities("Koramangala", List.of(WorkoutType.Cardio, WorkoutType.Swimming, WorkoutType.Weights, WorkoutType.Yoga));

        var center2 = centerService.addCenter("Bellandur");
        var timing3 = new Timing(7, 10);
        var timing4 = new Timing(19, 22);
        centerService.addCenterTimings("Bellandur", List.of(timing3, timing4));
        centerService.addCenterActivities("Bellandur", List.of(WorkoutType.Cardio, WorkoutType.Weights, WorkoutType.Yoga));


        centerService.addWorkout("Koramangala", WorkoutType.Weights, 6, 7, 100);
        centerService.addWorkout("Koramangala", WorkoutType.Cardio, 7, 8, 150);
        centerService.addWorkout("Koramangala", WorkoutType.Yoga, 8, 9, 200);


        centerService.addWorkout("Bellandur", WorkoutType.Weights, 18, 19, 100);
        centerService.addWorkout("Bellandur", WorkoutType.Swimming, 19, 20, 100);
        centerService.addWorkout("Bellandur", WorkoutType.Cardio, 19, 20, 20);
        centerService.addWorkout("Bellandur", WorkoutType.Weights, 20, 21, 100);
        centerService.addWorkout("Bellandur", WorkoutType.Weights, 21, 22, 100);

        var userService = new UserService(centerService);
        userService.addUser("Vaibhav");
        userService.viewWorkoutAvailability(WorkoutType.Weights);

        var bookingService = new BookingService(centerService, userService);
        bookingService.bookSession("Vaibhav", "Koramangala", WorkoutType.Weights,  6, 7);

    }
}
