package org.example.notificationsystem.model;

import lombok.AllArgsConstructor;

import java.util.Date;

@AllArgsConstructor
public class Notification {
    private User user;
    private Date timestamp;
    private NotificationType type;
    private INotificationTypeData typeData;
}
