package org.example.service;

import lombok.AllArgsConstructor;
import org.example.model.Concert;
import org.example.repository.IConcertsRepository;

@AllArgsConstructor
public class ConcertsService {
    private IConcertsRepository repository;

    public void addConcert(final Concert concert) {
        repository.addConcert(concert);
    }
}
