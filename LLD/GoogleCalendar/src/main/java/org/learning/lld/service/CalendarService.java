package org.learning.lld.service;

import lombok.NonNull;
import org.learning.lld.model.Event;
import org.learning.lld.actions.IPostEventAction;
import org.learning.lld.repository.IEventRepository;

public class CalendarService {
    private final IEventRepository eventRepository;
    private final IPostEventAction[] postEventActions;

    public CalendarService(@NonNull final IEventRepository eventRepository, IPostEventAction[] postEventActions) {
        this.eventRepository = eventRepository;
        this.postEventActions = postEventActions;
    }


    public void createEvent(@NonNull final Event event) {
        //validations
        //event owner is authorized on the current user (session of the user is same as owner)
        this.eventRepository.save(event);

        for (IPostEventAction postEventAction : postEventActions) {
            postEventAction.performAction();
        }
    }
}
