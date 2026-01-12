package org.example.notificationsystem.model;

public interface IChannelDataExtractor {
    boolean canHandle(ChannelType type);
    IChannelNotificationData extractData(User user, NotificationType type, INotificationTypeData typeData);
}
