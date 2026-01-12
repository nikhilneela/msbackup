package org.example.SharedPlaygroundWithFairness;

import lombok.SneakyThrows;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class Playground {
    private final int MAX_PLAYERS;
    private final HashMap<Team, Queue<Player>> playerQueues;
    private Team currentTeam;
    private int currentNumberOfPlayers;
    private Team lastPlayed;

    public Playground(int MAX_PLAYERS) {
        this.MAX_PLAYERS = MAX_PLAYERS;
        this.playerQueues = new HashMap<>();
        this.playerQueues.put(Team.A, new LinkedList<>());
        this.playerQueues.put(Team.B, new LinkedList<>());
        this.currentTeam = null;
        this.currentNumberOfPlayers = 0;
    }

    @SneakyThrows
    public void play(final Player player) {
        synchronized (this) {
            log("Player " + player.getName() + " waiting in the queue");
            Queue<Player> teamQueue = playerQueues.get(player.getTeam());
            teamQueue.add(player);

            while (!canEnter(player)) {
                this.wait();
            }

            if (currentNumberOfPlayers == 0) {
                log("Team " + player.getTeam() + " owns the ground");
            }
            lastPlayed = player.getTeam();
            currentTeam = player.getTeam();
            currentNumberOfPlayers++;
            remove(player);
            log("Player " + player.getName() + " started playing");
        }

        Thread.sleep(player.getPlayingTime());

        synchronized (this) {
            currentNumberOfPlayers--;
            if (currentNumberOfPlayers == 0) {
                log("Team " + player.getTeam() + " leaves ownership of the ground");
                currentTeam = null;
            }
            log("Player " + player.getName() + " finished playing");
            this.notifyAll();
        }
    }

    private boolean canEnter(final Player player) {
        if (currentNumberOfPlayers == MAX_PLAYERS) {
            return false;
        }

        Queue<Player> teamQueue = playerQueues.get(player.getTeam());
        if (!teamQueue.isEmpty() && teamQueue.peek() != player) {
            return false;
        }

        // If same team is playing, only allow more players if other team is NOT waiting
        if (currentTeam != null && currentTeam == player.getTeam()) {
            return true;
        }

        // If other team is playing, can't enter
        if (currentTeam != null) {
            return false;
        }

        // Ground empty case
        if (lastPlayed != null) {
            // If last played was the same team, only allow if other team not waiting
            return player.getTeam() != lastPlayed || !isOtherTeamPlayersWaiting(player.getTeam());
        }

        return true;
    }


    private boolean isOtherTeamPlayersWaiting(Team team) {
        for (Map.Entry<Team, Queue<Player>> entry : playerQueues.entrySet()) {
            if (entry.getKey() != team && !entry.getValue().isEmpty()) {
                return true;
            }
        }
        return false;
    }

    private void remove(final Player player) {
        this.playerQueues.get(player.getTeam()).remove(player);
    }

    private void log(final String message) {
        System.out.println("[" + LocalDateTime.now() + "] " + message);
    }
}
