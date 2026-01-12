package org.example.notificationsystem.strategy;

import org.example.notificationsystem.model.*;

public interface IChannelTypeHandler {
    boolean doesSupport(ChannelType type);
    void handle(IChannelNotificationData channelNotificationData);
}
