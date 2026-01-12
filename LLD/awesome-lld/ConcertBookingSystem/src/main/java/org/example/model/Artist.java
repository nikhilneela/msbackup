package org.example.model;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class Artist {
    private int id;
    private String name;
}
