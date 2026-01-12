package org.example.BathroomManagement;

import java.io.Console;
import java.util.Scanner;

public class BathroomManagementRunner {
    public static void main(String[] args) {
        BathroomManager bathroomManager = new BathroomManager(new Bathroom(3));
        //interactive(bathroomManager);
        //test_democrats_followed_by_republicans(bathroomManager);
        test_democrats_followed_by_republicans_2(bathroomManager);
    }

    private static void interactive(final BathroomManager bathroomManager) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("Enter your choice : ");
            String line = scanner.nextLine();
            if (line.isEmpty()) {
                continue;
            }
            String[] parts = line.split(" ");
            String party = parts[0];
            String name = parts[1];

            if (party.equals(PoliticalParty.D.name())) {
                new Thread(() -> bathroomManager.democrat(name)).start();
            } else if (party.equals(PoliticalParty.R.name())){
                new Thread(() -> bathroomManager.republican(name)).start();
            } else {
                break;
            }
        }
    }

    private static void test_democrats_followed_by_republicans(final BathroomManager bathroomManager) {
        new Thread(() -> bathroomManager.democrat("D1")).start();
        new Thread(() -> bathroomManager.democrat("D2")).start();
        new Thread(() -> bathroomManager.democrat("D3")).start();
        new Thread(() -> bathroomManager.democrat("D4")).start();
        new Thread(() -> bathroomManager.republican("R1")).start();
        new Thread(() -> bathroomManager.republican("R2")).start();
        new Thread(() -> bathroomManager.democrat("D5")).start();
        new Thread(() -> bathroomManager.republican("R3")).start();
        new Thread(() -> bathroomManager.democrat("D6")).start();
    }

    private static void test_democrats_followed_by_republicans_2(final BathroomManager bathroomManager) {
        new Thread(() -> bathroomManager.democrat("D1")).start();
        new Thread(() -> bathroomManager.democrat("D2")).start();
        new Thread(() -> bathroomManager.democrat("D3")).start();
        new Thread(() -> bathroomManager.democrat("D4")).start();
        new Thread(() -> bathroomManager.republican("R1")).start();
        new Thread(() -> bathroomManager.republican("R2")).start();
        new Thread(() -> bathroomManager.democrat("D5")).start();
        new Thread(() -> bathroomManager.democrat("D6")).start();
    }
}
