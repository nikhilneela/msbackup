package org.learning.lld.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter
public class Agent {
    private final String id;
    private final String email;
    private final String name;
    @Setter
    private AgentStatus agentStatus;
    private final List<TicketType> expertise;
}
