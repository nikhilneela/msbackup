package org.example.notificationsystem.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SMSChannelNotificationData implements IChannelNotificationData {
    private String phoneNumber;
    private String message;
}
