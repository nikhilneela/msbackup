package org.example.notificationsystem.model;

import lombok.AllArgsConstructor;
import org.example.notificationsystem.service.UserService;

@AllArgsConstructor
public class SMSChannelDataExtractor implements IChannelDataExtractor {
    private final UserService userService;
    @Override
    public boolean canHandle(ChannelType type) {
        return type == ChannelType.SMS;
    }

    @Override
    public IChannelNotificationData extractData(User user, NotificationType type, INotificationTypeData typeData) {
        switch (type) {
            case FRIEND_REQUEST :
                FriendRequestTypeData friendRequestTypeData = (FriendRequestTypeData) typeData;
                User requestor = userService.getUser(friendRequestTypeData.getSenderId());
                //do validations
                return new SMSChannelNotificationData(user.getPhoneNumber(), requestor.getName() + " has sent a friend request (Accept/Reject?)");
            case NEW_MESSAGE:
                NewMessageTypeData newMessageTypeData = (NewMessageTypeData) typeData;
                User sender = userService.getUser(newMessageTypeData.getUserId());
                return new SMSChannelNotificationData(user.getPhoneNumber(), "A new message has arrived from " + sender.getName() + "Message : " + newMessageTypeData.getMessage());
            case CUSTOM_EVENT:
                CustomEventTypeData customEventTypeData = (CustomEventTypeData) typeData;
                return new SMSChannelNotificationData(user.getPhoneNumber(), customEventTypeData.getCustomData1() + " " + customEventTypeData.getCustomData2());
            case SYSTEM_ALERT:
                SystemAlertTypeData systemAlertTypeData = (SystemAlertTypeData) typeData;
                return new SMSChannelNotificationData(user.getPhoneNumber(), systemAlertTypeData.getMessage());
            default:
                return null;
        }
    }
}
