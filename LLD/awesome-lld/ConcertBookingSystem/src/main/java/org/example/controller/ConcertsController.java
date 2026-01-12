package org.example.controller;

import lombok.AllArgsConstructor;
import org.example.model.Artist;
import org.example.model.Concert;
import org.example.model.Venue;
import org.example.service.ConcertsService;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@AllArgsConstructor
public class ConcertsController {
    private final ConcertsService service;

    public void addConcert(
            final String name,
            final String description,
            final Venue venue,
            final List<Artist> artists,
            final LocalDateTime time,
            final int durationInMinutes) {

        Concert concert = Concert.builder()
                // ID intentionally omitted — generated in service
                .name(name)
                .description(description)
                .venue(venue)
                .artists(artists == null ? Collections.emptyList() : List.copyOf(artists))
                .time(time)
                .durationInMinutes(durationInMinutes)
                .build();

        service.addConcert(concert);
    }
}
