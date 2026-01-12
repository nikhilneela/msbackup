package org.example.leaderboard;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@AllArgsConstructor
public class Player implements Comparable<Player> {
    private final String id;
    @Setter
    private int score;

    @Override
    public int compareTo(Player o) {
        return Integer.compare(score, o.score);
    }

    @Override
    public String toString() {
        return "(" + id + "," + score + ")";
    }
}
