package org.learning.lld.repository;

import lombok.NonNull;
import org.learning.lld.model.WorkItem;

import java.util.*;

public interface IWorkLogRepository {
    void saveWorkLog(@NonNull final WorkItem workItem);
    List<WorkItem> getWorkLog(@NonNull final String agentId);
    WorkItem getWorkItem(@NonNull final String agentId, @NonNull final String ticketId);
    void updateWorkLog(@NonNull final String agentId, @NonNull final String ticketId);
}
