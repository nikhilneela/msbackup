package org.learning.lld.pubsub;

import lombok.NonNull;
import org.learning.lld.model.Agent;
import org.learning.lld.model.Ticket;

public interface ITicketStateChangeSubscriber {
    void onTicketAssigned(@NonNull final Ticket ticket, @NonNull final Agent agent);
    void onTicketResolved(@NonNull final Ticket ticket, @NonNull final Agent agent);
}
