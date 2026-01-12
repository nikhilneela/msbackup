package org.splitwise.repository;

import org.splitwise.models.User;

import java.util.HashMap;
import java.util.Map;

public class UserRepository {
    Map<String, User> usersMap;

    public UserRepository() {
        usersMap = new HashMap<>();
    }

    public void addUser(User user) {
        usersMap.putIfAbsent(user.getUserId(), user);
    }

    public User getUser(String userId) {
        return usersMap.getOrDefault(userId, null);
    }

}
