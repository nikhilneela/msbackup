package org.example.notificationsystem.model;

import lombok.Getter;

import java.util.Map;

@Getter
public class User {
    private String id;
    private String name;
    private String phoneNumber;
    private String emailId;
    private UserPreference userPreferences;
}
