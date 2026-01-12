package org.example.notificationsystem.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.sql.Array;
import java.util.*;
import java.util.stream.Collectors;

@AllArgsConstructor
public class UserPreference {
    @Getter
    private Map<NotificationType, List<ChannelType>> notificationTypeListMap;
    private boolean none;

    public static UserPreference getDefaultUserPreferences() {
        List<ChannelType> allChannelTypes = new ArrayList<>(Arrays.asList(ChannelType.IN_APP, ChannelType.EMAIL, ChannelType.PUSH, ChannelType.SMS));

        Map<NotificationType, List<ChannelType>> preferences = new HashMap<>();

        for (NotificationType type : NotificationType.values()) {
            preferences.put(type, new ArrayList<>(allChannelTypes));
        }

        return new UserPreference(preferences, false);
    }

    public void optOutCompletely() {
        this.none = true;
    }

    public boolean hasOptedOutCompletely() {
        return this.none;
    }

    public void removeNotificationType(final NotificationType type, final List<ChannelType> channelTypes) {
        if (!notificationTypeListMap.containsKey(type)) {
            return;
        }
        HashSet<ChannelType> excludedChannels = new HashSet<>(channelTypes);
        List<ChannelType> filteredList =  notificationTypeListMap.get(type).stream().filter(channelType -> !excludedChannels.contains(channelType)).collect(Collectors.toList());
        notificationTypeListMap.put(type, filteredList);
    }

    public void removeNotificationType(final NotificationType type) {
        if (!notificationTypeListMap.containsKey(type)) {
            return;
        }
        notificationTypeListMap.remove(type);
    }

    public void removeNotificationType(final NotificationType type, final ChannelType channelType) {
        removeNotificationType(type, List.of(channelType));
    }

    public void addNotificationType(final NotificationType type, final List<ChannelType> channelTypes) {
        List<ChannelType> subscribedTypes = notificationTypeListMap.computeIfAbsent(type, (t) -> new ArrayList<>());
        HashSet<ChannelType> subscribedSet = new HashSet<>(subscribedTypes);
        subscribedSet.addAll(channelTypes);
        notificationTypeListMap.put(type, new ArrayList<>(subscribedSet));
        none = false;
    }

    public void addNotificationType(final NotificationType type) {
        addNotificationType(type, new ArrayList<>(Arrays.asList(ChannelType.IN_APP, ChannelType.EMAIL, ChannelType.PUSH, ChannelType.SMS)));
    }

    public List<ChannelType> getSubscribedChannels(final NotificationType type) {
        return notificationTypeListMap.getOrDefault(type, null);
    }
}

/*
Requirements,
- I want to subscribe to FRIEND_REQUEST and receive it on Email, InApp
- I want to subscribe to SYSTEM_ALERT and receive it on SMS
- I do not want to subscribe for any type of notifications
 */
