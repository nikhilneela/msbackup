package org.example.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@AllArgsConstructor
@Builder
@ToString
public class Concert {
    private String id;
    private String name;
    private String description;
    private Venue venue;
    private List<Artist> artists;
    private LocalDateTime time;
    private int durationInMinutes;
}
