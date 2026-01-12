package org.learning.lld.strategy;

import lombok.NonNull;
import org.learning.lld.model.Agent;

import java.util.List;

public interface IAgentAssignmentStrategy {
    Agent pickAgent(@NonNull final List<Agent> agents);
}
