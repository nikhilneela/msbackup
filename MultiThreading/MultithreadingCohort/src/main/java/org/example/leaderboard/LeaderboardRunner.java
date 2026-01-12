package org.example.leaderboard;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;

import java.util.List;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@AllArgsConstructor
public class LeaderboardRunner {
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    private final ScheduledExecutorService updateScheduler = Executors.newScheduledThreadPool(100);
    private final int numPlayers;
    private final int numUpdates;
    private final int delayInUpdatesInSeconds;
    private final int leaderBoardCallDelayInSeconds;
    private final Random random = new Random();
    private final LeaderboardService leaderboardService = new LeaderboardService();

    @SneakyThrows
    public void run() {
        scheduler.scheduleAtFixedRate(() -> print(leaderboardService.getLeaderboard()), 0, leaderBoardCallDelayInSeconds, TimeUnit.SECONDS);
        updateScheduler.scheduleAtFixedRate(() -> {
            int id = random.nextInt(numPlayers);
            int r_MAX = 300;
            int score = random.nextInt(r_MAX);
            leaderboardService.updateScore(String.valueOf(id), score);
        }, 0, delayInUpdatesInSeconds, TimeUnit.SECONDS);
    }

    private void print(List<Player> players) {
        players.forEach(player -> System.out.print(player + " "));
        System.out.println();
    }
}
