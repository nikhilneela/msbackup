package org.example.notificationsystem.repository;

import org.example.notificationsystem.model.User;

public interface IUserRepository {
    void addUser(User user);
    void removeUser(String id);
    User getUser(String id);
}
