package org.learning.lld.service;

import lombok.NonNull;
import org.learning.lld.exception.TicketAlreadyAssignedException;
import org.learning.lld.exception.NoAgentsAvailableForIssueType;
import org.learning.lld.model.*;
import org.learning.lld.pubsub.TicketStateChangePubSub;
import org.learning.lld.repository.ICustomerRepository;
import org.learning.lld.repository.ITicketRepository;
import org.learning.lld.strategy.IAgentAssignmentStrategy;

import java.util.List;
import java.util.UUID;

public class TicketService {
    private final ITicketRepository ticketRepository;
    private final AgentService agentsService;
    private final ICustomerRepository customerRepository;
    private final IAgentAssignmentStrategy agentAssignmentStrategy;
    public TicketService(@NonNull final AgentService agentsService, @NonNull final ITicketRepository ticketRepository, @NonNull final ICustomerRepository customerRepository, @NonNull final IAgentAssignmentStrategy agentAssignmentStrategy) {
        this.agentsService = agentsService;
        this.ticketRepository = ticketRepository;
        this.customerRepository = customerRepository;
        this.agentAssignmentStrategy = agentAssignmentStrategy;
    }

    public Ticket createTicket(@NonNull final String transactionId, @NonNull final TicketType type, @NonNull final String subject, @NonNull final String description, @NonNull final String customerEmailId) {
        String issueId = UUID.randomUUID().toString();

        Customer customer = customerRepository.getByEmail(customerEmailId);
        Ticket ticket = new Ticket(issueId, transactionId, subject, type, customer, TicketStatus.UNASSIGNED, null);
        this.ticketRepository.save(ticket);
        return ticket;
    }

    public Agent assignTicket(@NonNull final String ticketId) {
        //in the controller, find issue with the transactionId and pas it here, do validation in controller
        //business specific validation here
        Ticket ticket = this.ticketRepository.get(ticketId);
        if (TicketStatus.INPROGRESS == ticket.getStatus()) {
            throw new TicketAlreadyAssignedException();
        }

        List<Agent> agents = this.agentsService.findAgents(ticket.getTicketType());

        if (agents.isEmpty()) {
            throw new NoAgentsAvailableForIssueType();
        }

        //try to find a free agent and use a strategy to assign an agent
        //if there is no free agent, we use a strategy to assign agent from the pool of agents
        List<Agent> freeAgents = agents.stream().filter(agent -> AgentStatus.FREE == agent.getAgentStatus()).toList();
        Agent agentToAssign;
        if (!freeAgents.isEmpty()) {
            agentToAssign = this.agentAssignmentStrategy.pickAgent(freeAgents);
        } else {
            agentToAssign = this.agentAssignmentStrategy.pickAgent(agents);
        }
        this.agentsService.assignAgent(agentToAssign, ticket);
        return agentToAssign;
    }

    public void resolveTicket(@NonNull final String issueId, @NonNull final String resolution) {
        Ticket ticket = this.ticketRepository.get(issueId);
        if (ticket.getStatus() != TicketStatus.INPROGRESS) {
            throw new IllegalStateException(); //make custom exception with more details
        }
        ticket.setStatus(TicketStatus.RESOLVED);
        Agent agent = this.agentsService.findAgent(ticket);
        TicketStateChangePubSub.getInstance().notifyOnTicketAssigned(ticket, agent);
        this.agentsService.freeAgent(agent);
    }
}
