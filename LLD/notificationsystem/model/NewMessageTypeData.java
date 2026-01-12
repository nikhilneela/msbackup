package org.example.notificationsystem.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class NewMessageTypeData implements INotificationTypeData {
    private final String userId;
    private final String message;
}
