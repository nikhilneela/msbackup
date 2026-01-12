package org.learning.lld.controller;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.learning.lld.model.Agent;
import org.learning.lld.model.Ticket;
import org.learning.lld.model.TicketType;
import org.learning.lld.service.TicketService;

import java.util.Arrays;

@AllArgsConstructor
public class TicketController {
    private final TicketService ticketService;

    public String createTicket(@NonNull final String transactionId, @NonNull final String ticketTypeAsString, @NonNull final String subject, @NonNull final String description, @NonNull final String email) {
        //validations
        TicketType ticketType = Arrays.stream(TicketType.values()).filter(tt -> tt.name().equals(ticketTypeAsString)).findFirst().orElseThrow();
        Ticket ticket = this.ticketService.createTicket(transactionId, ticketType, subject, description, email);
        return ticket.getId();
    }

    public String assignTicket(@NonNull final String ticketId) {
        Agent agent = this.ticketService.assignTicket(ticketId);
        return agent.getId();
    }
}
