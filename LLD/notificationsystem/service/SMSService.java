package org.example.notificationsystem.service;

public class SMSService {
    void send(String phoneNumber, String data) {
        System.out.println("Sent SMS to " + phoneNumber + " with data = " + data);
    }
}
