package org.learning.lld.service;

import lombok.NonNull;
import org.learning.lld.model.*;
import org.learning.lld.repository.IAgentRepository;
import org.learning.lld.repository.IWorkLogRepository;

import java.time.LocalDateTime;
import java.util.*;

public class AgentService {
    private final IAgentRepository agentRepository;
    private final IWorkLogRepository workLogRepository;
    private final Map<String, List<Agent>> expertiseToAgentsMap;
    private final Map<String, Agent> issueToAgentMap;
    private final Map<String, Queue<Ticket>> agentsToWaitingIssuesMap;

    public AgentService(@NonNull final IAgentRepository agentRepository, @NonNull final IWorkLogRepository workLogRepository) {
        this.agentRepository = agentRepository;
        this.workLogRepository = workLogRepository;
        this.expertiseToAgentsMap = new HashMap<>();
        this.issueToAgentMap = new HashMap<>();
        this.agentsToWaitingIssuesMap = new HashMap<>();
    }

    public Agent addAgent(@NonNull final String name, @NonNull final String email, @NonNull final List<TicketType> expertise) {
        //validations
        //name, email validations should be done in API layer
        //only business specific validations should be done in this layer
        //for example, an agent should at least have 1 expertise and not more than 5 expertise
        //we can have expertises as another enum and later match issueType with expertiseType
        //for now, let's move with issueType as expertiseType
        String agentId = UUID.randomUUID().toString();
        Agent agent = new Agent(agentId, email, name, AgentStatus.FREE, expertise);
        this.agentRepository.save(agent);

        expertise.forEach(ticketType -> {
            List<Agent> agents = expertiseToAgentsMap.getOrDefault(ticketType.name(), new ArrayList<>());
            agents.add(agent);
            expertiseToAgentsMap.put(ticketType.name(), agents);
        });
        return agent;
    }

    public List<Agent> findAgents(@NonNull final TicketType expertise) {
        return expertiseToAgentsMap.getOrDefault(expertise.name(), new ArrayList<>());
    }

    public void assignAgent(@NonNull final Agent agent, @NonNull final Ticket ticket) {
        //if the agent is free, mark him/her busy
        //is the agent is busy, add it to his queue

        if (AgentStatus.FREE == agent.getAgentStatus()) {
            agent.setAgentStatus(AgentStatus.BUSY);
            issueToAgentMap.put(ticket.getId(), agent);
        } else {
            Queue<Ticket> tickets = agentsToWaitingIssuesMap.getOrDefault(agent.getId(), new LinkedList<>());
            tickets.add(ticket);
            agentsToWaitingIssuesMap.put(agent.getId(), tickets);
        }
    }

    public void freeAgent(@NonNull final Agent agent) {
        //mark the status as free
        //need mechanism to track work history here, or should it go to issue service ?
        agent.setAgentStatus(AgentStatus.FREE);
        if (agentsToWaitingIssuesMap.containsKey(agent.getId())) {
            Queue<Ticket> waitingTickets = agentsToWaitingIssuesMap.get(agent.getId());
            Ticket ticket = waitingTickets.poll();

            if (waitingTickets.isEmpty()) {
                agentsToWaitingIssuesMap.remove(agent.getId());
            }

            if (ticket != null) {
                assignAgent(agent, ticket);
            }
        }
    }

    public Agent findAgent(@NonNull final Ticket ticket) {
        return this.issueToAgentMap.getOrDefault(ticket.getId(), null);
    }

    public void createWorkLog(@NonNull final Agent agent, @NonNull final Ticket ticket) {
        String workItemId = UUID.randomUUID().toString();
        WorkItem workItem = new WorkItem(workItemId, agent.getId(), ticket.getId(), LocalDateTime.now(), null);
        workLogRepository.saveWorkLog(workItem);
    }

    public void updateWorkLog(@NonNull final Agent agent, @NonNull final Ticket ticket) {
        workLogRepository.updateWorkLog(agent.getId(), ticket.getId());
    }

    public Agent getAgent(@NonNull final String id) {
        return this.agentRepository.get(id);
    }
}
