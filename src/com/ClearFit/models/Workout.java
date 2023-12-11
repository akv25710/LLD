package com.lld.models;

import java.util.ArrayList;
import java.util.List;

public class Workout {
    private final WorkoutType type;
    private final List<Timing> timingList;


    public Workout(WorkoutType type) {
        this.type = type;
        this.timingList = new ArrayList<>();
    }

    public void addTiming(Timing timing) {
        this.timingList.add(timing);
    }

    public WorkoutType getType() {
        return type;
    }

    public boolean isTimingAvailable(Timing timing) {
        var startTime = timing.getStartTime();
        var endTime = timing.getEndTime();
        for (var entry: timingList) {
            if (startTime > entry.getStartTime() && endTime > entry.getStartTime() &&
                    endTime < entry.getEndTime() && startTime < entry.getEndTime()) {
                return true;
            }
        }

        return false;
    }
}
