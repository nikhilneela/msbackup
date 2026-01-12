package org.learning.lld.strategy;

import lombok.NonNull;
import org.learning.lld.model.Agent;

import java.util.List;
import java.util.Random;

public class RandomAgentAssignmentStrategy implements IAgentAssignmentStrategy {
    private final Random random = new Random();
    @Override
    public Agent pickAgent(@NonNull List<Agent> agents) {
        if (agents.isEmpty()) {
            return null; // Or handle this case differently based on your requirements
        }
        // Randomly select an agent from the list
        int randomIndex = random.nextInt(agents.size());
        return agents.get(randomIndex);
    }
}
