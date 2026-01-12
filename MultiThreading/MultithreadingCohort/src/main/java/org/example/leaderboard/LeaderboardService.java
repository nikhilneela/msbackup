package org.example.leaderboard;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class LeaderboardService {
    private final Map<String, Player> playersMap;
    private final LeaderboardState state;

    public LeaderboardService() {
        this.playersMap = new ConcurrentHashMap<>();
        state = new LeaderboardState(playersMap, false);
        LeaderboardWorker worker = new LeaderboardWorker(state);
        new Thread(worker).start();
    }

    public void updateScore(String playerId, int score) {
        Player player = playersMap.computeIfAbsent(playerId, id -> new Player(id, 0));
        synchronized (player) {
            player.setScore(player.getScore() + score);
        }

        synchronized (state) {
            state.setShouldRun(true);
            state.notifyAll();
        }
    }

    public List<Player> getLeaderboard() {
        List<Player> o = state.getOrderedBoard();
        return o == null ? new ArrayList<>() : o;
    }
}
