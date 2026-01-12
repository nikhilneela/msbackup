package org.example.repository;

import org.example.model.Concert;
import org.example.model.Venue;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class InMemoryConcertsRepository implements IConcertsRepository {
    private List<Concert> concerts;
    private List<Venue> venues;

    public InMemoryConcertsRepository() {
        concerts = new ArrayList<>();
        venues = new ArrayList<>();
    }


    @Override
    public void addConcert(Concert concert) {
        Concert _concert = Concert.builder()
                .id(UUID.randomUUID().toString())
                .time(concert.getTime())
                .name(concert.getName())
                .venue(concert.getVenue())
                .artists(concert.getArtists())
                .description(concert.getDescription())
                .durationInMinutes(concert.getDurationInMinutes())
                .build();
        concerts.add(_concert);
    }

    @Override
    public void removeConcert(String id) {

    }

    @Override
    public List<Concert> getConcerts() {
        return concerts;
    }
}
