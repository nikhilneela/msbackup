package org.example.notificationsystem.strategy;

import lombok.AllArgsConstructor;
import org.example.notificationsystem.model.*;
import org.example.notificationsystem.service.SMSChannelNotificationService;

@AllArgsConstructor
public class SMSChannelTypeHandler implements IChannelTypeHandler {
    private final SMSChannelNotificationService service;

    @Override
    public boolean doesSupport(ChannelType type) {
        return type == ChannelType.SMS;
    }

    @Override
    public void handle(IChannelNotificationData channelNotificationData) {
        service.send(channelNotificationData);
    }
}
