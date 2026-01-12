package org.example.leaderboard;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Getter
public class LeaderboardState {
    private final Map<String, Player> playersMap;
    @Setter
    private boolean shouldRun;
    @Setter
    private List<Player> orderedBoard;

    public LeaderboardState(Map<String, Player> playersMap, boolean shouldRun) {
        this.playersMap = playersMap;
        this.shouldRun = shouldRun;
    }
}
