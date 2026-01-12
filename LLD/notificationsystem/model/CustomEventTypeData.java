package org.example.notificationsystem.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CustomEventTypeData implements INotificationTypeData {
    private final String customData1;
    private final String customData2;
}
