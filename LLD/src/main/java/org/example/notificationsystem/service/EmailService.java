package org.example.notificationsystem.service;

public class EmailService {
    public void sendEmail(String to, String subject, String body) {
        System.out.println("Sent email to " + to + " with subject = " + subject + " and body = " + body);
    }
}
