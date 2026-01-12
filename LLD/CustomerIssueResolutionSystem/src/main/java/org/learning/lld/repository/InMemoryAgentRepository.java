package org.learning.lld.repository;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.learning.lld.model.Agent;

import java.util.ArrayList;
import java.util.List;

public class InMemoryAgentRepository implements IAgentRepository {
    private final List<Agent> agents;

    public InMemoryAgentRepository() {
        this.agents = new ArrayList<>();
    }

    public void save(@NonNull final Agent agent) {
        this.agents.add(agent);
    }

    public Agent get(@NonNull final String id) {
        return this.agents.stream().filter(agent -> agent.getId().equals(id)).findFirst().orElse(null);
    }
}
