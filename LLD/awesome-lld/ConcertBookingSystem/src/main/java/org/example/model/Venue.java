package org.example.model;

import lombok.Builder;
import lombok.Getter;

import java.util.List;
@Getter
@Builder
public class Venue {
    private int id;
    private String name;
    private List<Seat> seats;
}
