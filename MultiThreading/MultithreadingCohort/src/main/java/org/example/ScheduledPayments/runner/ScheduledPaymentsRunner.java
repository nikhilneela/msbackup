package org.example.ScheduledPayments.runner;

import lombok.SneakyThrows;
import org.example.ScheduledPayments.Logger.ConsoleLogger;
import org.example.ScheduledPayments.model.Account;
import org.example.ScheduledPayments.service.AccountService;
import org.example.ScheduledPayments.service.PaymentService;

public class ScheduledPaymentsRunner {
    @SneakyThrows
    public static void main(String[] args) {
        AccountService accountService = new AccountService();

        Account ac001 = accountService.addAccount("AC001", 1000);
        Account ac002 = accountService.addAccount("AC002", 1000);
        Account ac003 = accountService.addAccount("AC003", 1000);

        PaymentService paymentService = new PaymentService(accountService, new ConsoleLogger());


        paymentService.schedulePayment(ac001.getId(), ac002.getId(), System.currentTimeMillis() + 1000, 10, 10.0);
        paymentService.schedulePayment(ac002.getId(), ac003.getId(), System.currentTimeMillis() + 2000, 15, 20.0);

        Thread.sleep(10*1000);

        System.out.println(ac001);
        System.out.println(ac002);
        System.out.println(ac003);
    }
}
