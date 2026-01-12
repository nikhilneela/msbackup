package org.learning.lld.repository;

import lombok.NonNull;
import org.learning.lld.model.Ticket;

public interface ITicketRepository {
    void save(@NonNull final Ticket ticket);
    Ticket get(@NonNull final String id);
}
