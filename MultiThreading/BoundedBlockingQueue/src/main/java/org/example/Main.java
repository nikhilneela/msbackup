package org.example;

import org.example.runners.BoundedBlockingQueueRunner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        BoundedBlockingQueueRunner runner = new BoundedBlockingQueueRunner();
        runner.run();
    }
}