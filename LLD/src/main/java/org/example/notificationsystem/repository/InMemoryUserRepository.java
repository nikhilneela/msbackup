package org.example.notificationsystem.repository;

import org.example.notificationsystem.model.User;

import java.util.HashMap;

public class InMemoryUserRepository implements IUserRepository {
    private final HashMap<String, User> idMap;

    public InMemoryUserRepository() {
        idMap = new HashMap<>();
    }

    @Override
    public void addUser(User user) {
        idMap.put(user.getId(), user);
    }

    @Override
    public void removeUser(String id) {
        idMap.remove(id);
    }

    @Override
    public User getUser(String id) {
        if (!idMap.containsKey(id)) {
            return null;
        }
        return idMap.get(id); //should return immutable?
    }
}
