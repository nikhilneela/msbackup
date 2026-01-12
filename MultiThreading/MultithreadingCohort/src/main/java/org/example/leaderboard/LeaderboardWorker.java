package org.example.leaderboard;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;

import java.util.ArrayList;
import java.util.Collections;

@AllArgsConstructor
public class LeaderboardWorker implements Runnable {
    private final LeaderboardState state;

    @SneakyThrows
    @Override
    public void run() {
        synchronized (state) {
            while (true) {
                if (!state.isShouldRun()) {
                    state.wait();
                }
                state.setShouldRun(false);
                ArrayList<Player> players = new ArrayList<>(state.getPlayersMap().values());
                state.setOrderedBoard(players.stream().map(player -> new Player(player.getId(), player.getScore())).sorted(Collections.reverseOrder()).toList());
            }
        }
    }
}
