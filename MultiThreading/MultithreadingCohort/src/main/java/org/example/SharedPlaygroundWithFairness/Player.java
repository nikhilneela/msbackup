package org.example.SharedPlaygroundWithFairness;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.example.SharedPlaygroundWithFairness.Team;

import java.util.Objects;

@AllArgsConstructor
@Getter
public class Player {
    private String name;
    private Team team;
    private Long playingTime;
    private Long entryTime;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return Objects.equals(name, player.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }
}
