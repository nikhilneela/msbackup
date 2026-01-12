package org.learning.lld.pubsub;

import lombok.NonNull;
import org.learning.lld.model.Agent;
import org.learning.lld.model.Ticket;

import java.util.ArrayList;
import java.util.List;

//this should be a singleton
public class TicketStateChangePubSub {

    private static TicketStateChangePubSub instance;

    private final List<ITicketStateChangeSubscriber> subscribers;

    private TicketStateChangePubSub() {
        this.subscribers = new ArrayList<>();
    }

    public static TicketStateChangePubSub getInstance() {
        if (instance == null) {
            synchronized (TicketStateChangePubSub.class) {
                if (instance == null) {
                    instance = new TicketStateChangePubSub();
                }
            }
        }
        return instance;
    }

    public void addSubscribers(ITicketStateChangeSubscriber ticketResolutionSubscriber) {
        synchronized (subscribers) {
            this.subscribers.add(ticketResolutionSubscriber);
        }
    }

    public void notifyOnTicketAssigned(@NonNull final Ticket ticket, @NonNull final Agent agent) {
        synchronized (subscribers) {
            for(ITicketStateChangeSubscriber ticketStateChangeSubscriber : subscribers) {
                ticketStateChangeSubscriber.onTicketAssigned(ticket, agent);
            }
        }
    }

    public void notifyOnTicketResolved(@NonNull final Ticket ticket, @NonNull final Agent agent) {
        synchronized (subscribers) {
            for(ITicketStateChangeSubscriber ticketStateChangeSubscriber : subscribers) {
                ticketStateChangeSubscriber.onTicketResolved(ticket, agent);
            }
        }
    }
}
