package org.example.notificationsystem.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class SystemAlertTypeData implements INotificationTypeData {
    private final String message;
}
