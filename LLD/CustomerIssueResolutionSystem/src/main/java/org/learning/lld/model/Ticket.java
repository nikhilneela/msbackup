package org.learning.lld.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
public class Ticket {
    private final String id;
    private final String transactionId;
    private final String subject;
    private final TicketType ticketType;
    private final Customer customer;
    @Setter
    private TicketStatus status;
    private String resolution;
}
