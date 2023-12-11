package com.lld.services;

import com.lld.models.User;
import com.lld.models.WorkoutType;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class UserService {

    private final Map<String, User> userMap;
    private final CenterService centerService;

    public UserService(CenterService centerService) {
        this.centerService = centerService;
        this.userMap = new HashMap<>();
    }

    public User addUser(String name) {
        var id = UUID.randomUUID().toString();
        var user = new User(id, name);
        userMap.put(user.getName(), user);
        return user;
    }

    public User getUser(String name) {
        if (userMap.containsKey(name)) {
            return userMap.get(name);
        }

        return null;
    }

    public void viewWorkoutAvailability(WorkoutType type) {
        var result = centerService.viewWorkoutAvailability(type);
        System.out.println(result);
    }
}
