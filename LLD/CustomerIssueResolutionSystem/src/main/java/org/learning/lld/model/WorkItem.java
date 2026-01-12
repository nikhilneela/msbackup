package org.learning.lld.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
public class WorkItem {
    private final String id;
    @Getter
    private final String agentId;
    @Getter
    private final String ticketId;
    private final LocalDateTime assignedAt;
    @Setter
    private LocalDateTime resolvedAt;
}
