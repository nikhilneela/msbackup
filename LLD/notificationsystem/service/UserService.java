package org.example.notificationsystem.service;

import lombok.AllArgsConstructor;
import org.example.notificationsystem.model.User;
import org.example.notificationsystem.repository.IUserRepository;

@AllArgsConstructor
public class UserService {
    private final IUserRepository userRepository;

    public void addUser(final User user) {
        userRepository.addUser(user);
    }

    public void removeUser(final String id) {
        userRepository.removeUser(id);
    }

    public User getUser(final String id) {
        return userRepository.getUser(id);
    }
}
