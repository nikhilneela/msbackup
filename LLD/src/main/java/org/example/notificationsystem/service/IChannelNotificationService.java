package org.example.notificationsystem.service;

import org.example.notificationsystem.model.IChannelNotificationData;

public interface IChannelNotificationService {
    void send(IChannelNotificationData data);
}
