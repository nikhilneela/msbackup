package org.learning.lld.repository;

import lombok.NonNull;
import org.learning.lld.model.WorkItem;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryWorkLogRepository implements IWorkLogRepository {
    private final Map<String, List<WorkItem>> agentToWorkLogMap;

    public InMemoryWorkLogRepository() {
        this.agentToWorkLogMap = new HashMap<>();
    }

    @Override
    public void saveWorkLog(@NonNull final WorkItem workItem) {
        List<WorkItem> worksLogs = this.agentToWorkLogMap.getOrDefault(workItem.getAgentId(), new ArrayList<>());
        worksLogs.add(workItem);
        this.agentToWorkLogMap.put(workItem.getAgentId(), worksLogs);
    }

    @Override
    public List<WorkItem> getWorkLog(@NonNull final String agentId) {
        return this.agentToWorkLogMap.getOrDefault(agentId, new ArrayList<>());
    }

    @Override
    public WorkItem getWorkItem(@NonNull final String agentId, @NonNull final String ticketId) {
        List<WorkItem> workItems = getWorkLog(agentId);
        return workItems.stream().filter(workItem -> workItem.getTicketId().equals(ticketId)).findFirst().orElseThrow();
    }

    @Override
    public void updateWorkLog(@NonNull final String agentId, @NonNull final String ticketId) {
        WorkItem workItem = getWorkItem(agentId, ticketId);
        workItem.setResolvedAt(LocalDateTime.now());
    }
}
