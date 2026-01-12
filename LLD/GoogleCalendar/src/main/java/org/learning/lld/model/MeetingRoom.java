package org.learning.lld.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class MeetingRoom {
    private final String id;
    private final String name;
    private final String buildingName;
    private final Integer floor;
    private final Integer number;
    private final MeetingRoomSize meetingRoomSize;
}
