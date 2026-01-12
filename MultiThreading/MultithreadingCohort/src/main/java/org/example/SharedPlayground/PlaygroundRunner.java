package org.example.SharedPlayground;

import lombok.SneakyThrows;

public class PlaygroundRunner {
    @SneakyThrows
    public static void main(String[] args) {
        //scenario1();
        scenario2();
    }

    @SneakyThrows
    public static void scenario1() {
        Playground playground = new Playground(3);

        new Thread(() -> playground.play(new Player("A1", Team.A, 5*1000L, System.currentTimeMillis()))).start();
        Thread.sleep(500);
        new Thread(() -> playground.play(new Player("A2", Team.A, 3*1000L, System.currentTimeMillis()))).start();
        Thread.sleep(500);
        new Thread(() -> playground.play(new Player("A3", Team.A, 2*1000L, System.currentTimeMillis()))).start();
        Thread.sleep(500);
        new Thread(() -> playground.play(new Player("B1", Team.B, 8*1000L, System.currentTimeMillis()))).start();
        Thread.sleep(500);
        new Thread(() -> playground.play(new Player("B2", Team.B, 1*1000L, System.currentTimeMillis()))).start();
        Thread.sleep(500);
        new Thread(() -> playground.play(new Player("A4", Team.A, 6*1000L, System.currentTimeMillis()))).start();
        Thread.sleep(500);
        new Thread(() -> playground.play(new Player("A5", Team.A, 4*1000L, System.currentTimeMillis()))).start();
        Thread.sleep(500);
        new Thread(() -> playground.play(new Player("A6", Team.A, 2*1000L, System.currentTimeMillis()))).start();
        Thread.sleep(500);
        new Thread(() -> playground.play(new Player("B3", Team.B, 8*1000L, System.currentTimeMillis()))).start();
        Thread.sleep(500);
        new Thread(() -> playground.play(new Player("B4", Team.B, 9*1000L, System.currentTimeMillis()))).start();
        Thread.sleep(500);
        new Thread(() -> playground.play(new Player("B5", Team.B, 1*1000L, System.currentTimeMillis()))).start();
        Thread.sleep(500);
        new Thread(() -> playground.play(new Player("A7", Team.A, 6*1000L, System.currentTimeMillis()))).start();
        Thread.sleep(500);
        new Thread(() -> playground.play(new Player("A8", Team.A, 4*1000L, System.currentTimeMillis()))).start();
    }

    @SneakyThrows
    public static void scenario2() {
        Playground playground = new Playground(3);

        new Thread(() -> playground.play(new Player("A1", Team.A, 5*1000L, System.currentTimeMillis()))).start();
        Thread.sleep(500);

        new Thread(() -> playground.play(new Player("A2", Team.A, 6*1000L, System.currentTimeMillis()))).start();
        Thread.sleep(500);

        new Thread(() -> playground.play(new Player("B1", Team.B, 8*1000L, System.currentTimeMillis()))).start();
        Thread.sleep(500);

        new Thread(() -> playground.play(new Player("B2", Team.B, 1*1000L, System.currentTimeMillis()))).start();
        Thread.sleep(500);

        new Thread(() -> playground.play(new Player("A3", Team.A, 9*1000L, System.currentTimeMillis()))).start();
        Thread.sleep(500);

        new Thread(() -> playground.play(new Player("A4", Team.A, 4*1000L, System.currentTimeMillis()))).start();
        Thread.sleep(500);

        new Thread(() -> playground.play(new Player("B3", Team.B, 3*1000L, System.currentTimeMillis()))).start();
        Thread.sleep(500);

        new Thread(() -> playground.play(new Player("B4", Team.B, 7*1000L, System.currentTimeMillis()))).start();
        Thread.sleep(500);
    }
}
