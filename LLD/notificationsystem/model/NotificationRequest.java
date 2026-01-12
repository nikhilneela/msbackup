package org.example.notificationsystem.model;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class NotificationRequest {
    private String receiverId;
    private NotificationType type;
    private Date timestamp;
    private Map<String, Object> data;
}
