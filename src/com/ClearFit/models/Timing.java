package com.lld.models;

public class Timing {
    private final Integer startTime;
    private final Integer endTime;

    public Timing(Integer startTime, Integer endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Integer getEndTime() {
        return endTime;
    }

    public Integer getStartTime() {
        return startTime;
    }
}
