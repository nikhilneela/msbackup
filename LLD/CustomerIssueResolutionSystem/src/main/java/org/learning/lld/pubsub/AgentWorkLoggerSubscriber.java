package org.learning.lld.pubsub;

import lombok.NonNull;
import org.learning.lld.model.Agent;
import org.learning.lld.model.Ticket;
import org.learning.lld.service.AgentService;

public class AgentWorkLoggerSubscriber implements ITicketStateChangeSubscriber {

    private final AgentService agentService;

    public AgentWorkLoggerSubscriber(@NonNull final AgentService agentService) {
        this.agentService = agentService;
    }


    @Override
    public void onTicketAssigned(@NonNull Ticket ticket, @NonNull Agent agent) {
        agentService.createWorkLog(agent, ticket);
    }

    @Override
    public void onTicketResolved(Ticket ticket, Agent agent) {
        agentService.updateWorkLog(agent, ticket);
    }
}
