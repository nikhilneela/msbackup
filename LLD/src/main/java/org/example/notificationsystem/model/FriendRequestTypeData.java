package org.example.notificationsystem.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class FriendRequestTypeData implements INotificationTypeData {
    private final String senderId;
    //can get all details from Id
}
