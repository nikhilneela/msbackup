package org.learning.lld.repository;

import lombok.NonNull;
import org.learning.lld.model.Ticket;

import java.util.ArrayList;
import java.util.List;

public class InMemoryTicketRepository implements ITicketRepository {
    private final List<Ticket> tickets;

    public InMemoryTicketRepository() {
        this.tickets = new ArrayList<>();
    }

    @Override
    public void save(@NonNull Ticket ticket) {
        this.tickets.remove(ticket);
        this.tickets.add(ticket);
    }

    @Override
    public Ticket get(@NonNull String id) {
        return this.tickets.stream().filter(issue -> issue.getId().equals(id)).findFirst().orElse(null);
    }
}
