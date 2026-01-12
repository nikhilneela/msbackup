package org.example.repository;

import org.example.model.Concert;

import java.util.List;

public interface IConcertsRepository {
    void addConcert(final Concert concert);
    void removeConcert(final String id);
    List<Concert> getConcerts();
}
