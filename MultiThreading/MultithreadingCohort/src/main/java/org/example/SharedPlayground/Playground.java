package org.example.SharedPlayground;

import lombok.SneakyThrows;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class Playground {
    private int currentNumberOfPlayers;
    private final int MAX_NUMBER_PLAYERS;
    private Team currentTeam;
    private HashMap<Team, Queue<Player>> playerQueues;

    public Playground(int MAX_NUMBER_PLAYERS) {
        this.MAX_NUMBER_PLAYERS = MAX_NUMBER_PLAYERS;
        currentTeam = null;
        currentNumberOfPlayers = 0;
        playerQueues = new HashMap<>();
        playerQueues.put(Team.A, new LinkedList<>());
        playerQueues.put(Team.B, new LinkedList<>());
    }

    @SneakyThrows
    public void play(final Player player) {
        //first add player to the waiting queue


        synchronized (this) {
            playerQueues.get(player.getTeam()).add(player);
            log("Player " + player.getName() + " is waiting in the Queue");

            while (!canEnter(player)) {
                this.wait();
            }

            if (currentNumberOfPlayers == 0) {
                log("Team " + player.getTeam() + " owns the ground");
            }

            currentNumberOfPlayers++;
            currentTeam = player.getTeam();
            remove(player);
        }

        log("Player " + player.getName() + " started playing");
        Thread.sleep(player.getPlayingTime());
        log("Player " + player.getName() + " finished playing");

        synchronized (this) {
            currentNumberOfPlayers--;
            if (currentNumberOfPlayers == 0) {
                log("Team  " + player.getTeam() + " left ownership of the ground");
                currentTeam = null;
            }
            this.notifyAll();
        }
    }

    private boolean canEnter(final Player player) {
        if (currentTeam == null) {
            return true;
        }

        if (currentNumberOfPlayers >= MAX_NUMBER_PLAYERS || player.getTeam() != currentTeam) {
            return false;
        }

        Queue<Player> thisTeamQueue = playerQueues.get(player.getTeam());

        return !thisTeamQueue.isEmpty() && thisTeamQueue.peek().getName().equals(player.getName());
    }

    private void remove(final Player player) {
        Queue<Player> thisTeamQueue = playerQueues.get(player.getTeam());
        thisTeamQueue.remove(player);
    }

    private void log(final String message) {
        System.out.println("[" + LocalDateTime.now() + "] " + message);
    }
}
