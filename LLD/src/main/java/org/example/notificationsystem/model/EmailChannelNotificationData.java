package org.example.notificationsystem.model;

import lombok.Getter;

@Getter
public class EmailChannelNotificationData implements IChannelNotificationData {
    private String emailId;
    private String subject;
    private String body;
}
