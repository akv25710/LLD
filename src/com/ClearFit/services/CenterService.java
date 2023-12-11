package com.lld.services;

import com.lld.models.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class CenterService {
    private final Map<String, Center> centerMap;

    public CenterService() {
        this.centerMap = new HashMap<>();
    }

    public Center addCenter(String name) {
        if (centerMap.containsKey(name)) {
            throw new IllegalStateException("center already added");
        }
        var id  = UUID.randomUUID().toString();
        var center = new Center(id, name);
        centerMap.put(center.getName(), center);
        System.out.println("Created center: " + center.getName());
        return center;
    }

    public Center getCenter(String name) {
        if (!centerMap.containsKey(name)) {
            throw new IllegalStateException("center not found");
        }
        return centerMap.get(name);
    }

    public void addCenterTimings(String name, List<Timing> timingList) {
        var center = getCenter(name);
        for (var timing: timingList) {
            center.addTiming(timing);
            System.out.println("Center: " + name + " timing added from " + timing.getStartTime() + " to " + timing.getEndTime());
        }
    }

    public void addCenterActivities(String name, List<WorkoutType> activities) {
        var center = getCenter(name);
        for (var activity: activities) {
            center.addActivity(activity);
        }
    }

    public void addWorkout(String name, WorkoutType workoutType, Integer startTime, Integer endTime, Integer capacity) {
        var center = getCenter(name);
        center.addWorkoutDetails(workoutType, startTime, endTime, capacity);
    }

    public HashMap<String, Map.Entry<Workout, Integer> > viewWorkoutAvailability(WorkoutType type) {
        HashMap<String, Map.Entry<Workout, Integer> > result = new HashMap<>();
        for (var entry: centerMap.entrySet()) {
            var center = entry.getValue();
            var workout = center.getAvailableSlot(type);
            if (workout != null) {
                result.put(center.getName(), workout);
            }
        }

        return result;
    }

    public boolean isWorkoutAvailable(String name, WorkoutType workoutType, Timing timing) {
        var center = getCenter(name);
        return center.isBookingAvailable(workoutType, timing);
    }

    public void bookSession(User user, String name, WorkoutType workoutType, Timing timing) {
        var center = getCenter(name);
        try {
            center.addBooking(user, workoutType, timing);
        } catch (InterruptedException e) {
            System.out.println("Failed to book session");
        }

    }

    public void cancelSession(User user, String name, WorkoutType workoutType, Timing timing) {
        var center = getCenter(name);
        try {
            center.cancelSession(user, workoutType, timing);
        } catch (InterruptedException e) {
            System.out.println("Failed to cancel session");
        }
    }


}
















