package org.example.notificationsystem.service;

import lombok.AllArgsConstructor;
import org.example.notificationsystem.model.EmailChannelNotificationData;
import org.example.notificationsystem.model.IChannelNotificationData;

@AllArgsConstructor
public class EmailChannelNotificationService implements IChannelNotificationService {
    private final EmailService emailService;

    @Override
    public void send(IChannelNotificationData data) {
        EmailChannelNotificationData notificationData = (EmailChannelNotificationData) data;
        emailService.sendEmail(notificationData.getEmailId(), notificationData.getSubject(), notificationData.getBody());
    }
}
