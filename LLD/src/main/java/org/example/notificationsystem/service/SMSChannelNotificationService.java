package org.example.notificationsystem.service;

import lombok.AllArgsConstructor;
import org.example.notificationsystem.model.IChannelNotificationData;
import org.example.notificationsystem.model.SMSChannelNotificationData;

@AllArgsConstructor
public class SMSChannelNotificationService implements IChannelNotificationService {
    private final SMSService smsService;
    @Override
    public void send(IChannelNotificationData data) {
        SMSChannelNotificationData notificationData = (SMSChannelNotificationData) data;
        smsService.send(notificationData.getPhoneNumber(), notificationData.getMessage());
    }
}
