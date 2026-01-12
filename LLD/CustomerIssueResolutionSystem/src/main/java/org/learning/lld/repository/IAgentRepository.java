package org.learning.lld.repository;

import lombok.NonNull;
import org.learning.lld.model.Agent;


public interface IAgentRepository {
    void save(@NonNull final Agent agent);
    Agent get(@NonNull final String id);
}
