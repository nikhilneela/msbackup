package org.example.notificationsystem.controller;

import lombok.AllArgsConstructor;
import org.example.notificationsystem.model.*;
import org.example.notificationsystem.service.UserService;
import org.example.notificationsystem.strategy.IChannelTypeHandler;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
public class NotificationController {
    private final UserService userService;
    private final List<IChannelDataExtractor> extractors;
    private final List<IChannelTypeHandler> handlers;

    public void sendNotification(final String receiverId, final NotificationType type, final INotificationTypeData typeData) {
        User user = userService.getUser(receiverId);
        Notification notification = new Notification(user, new Date(), type, typeData);
        UserPreference userPreference = user.getUserPreferences();
        List<ChannelType> subscribedChannels =  userPreference.getSubscribedChannels(type);
        if (subscribedChannels == null || userPreference.hasOptedOutCompletely()) {
            return;
        }

        for (ChannelType channelType : subscribedChannels) {
            for (IChannelDataExtractor extractor : extractors) {
                if (extractor.canHandle(channelType)) {
                    IChannelNotificationData notificationData = extractor.extractData(user, type, typeData);
                    IChannelTypeHandler handler = findHandler(channelType);
                    handler.handle(notificationData);
                }
            }
        }
    }

    private IChannelTypeHandler findHandler(final ChannelType type) {
        return handlers.stream().filter(iChannelTypeHandler -> iChannelTypeHandler.doesSupport(type)).findFirst().orElseThrow();
    }
}
