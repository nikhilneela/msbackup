package org.learning.lld.repository;

import lombok.NonNull;
import org.learning.lld.model.Event;

public interface IEventRepository {
    void save(@NonNull final Event event);

}
