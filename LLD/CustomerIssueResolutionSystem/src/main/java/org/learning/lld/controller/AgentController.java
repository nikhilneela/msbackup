package org.learning.lld.controller;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.learning.lld.model.Agent;
import org.learning.lld.model.TicketType;
import org.learning.lld.service.AgentService;

import java.util.Arrays;
import java.util.List;

@AllArgsConstructor
public class AgentController {
    private final AgentService agentService;
    public String createAgent(@NonNull final String name, @NonNull final String email, @NonNull final List<String> expertiseAsString) {
        //validations
        //is called by admin (privilige check)
        //email in right format
        //map expertise to list of enums we have, bail if see expertise not in what we support
        List<TicketType> expertise = expertiseAsString.stream().map(s -> Arrays.stream(TicketType.values()).filter(ticketType -> ticketType.name().equals(s)).findFirst().orElseThrow()).toList();
        Agent agent = this.agentService.addAgent(name, email, expertise);
        return agent.getId();
    }

    public Agent getAgent(@NonNull final String id) {
        return this.agentService.getAgent(id);
    }
}
