package org.example.ScheduledPayments.service;

import org.example.ScheduledPayments.model.Account;

import java.util.HashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class AccountService {
    private final HashMap<String, Lock> accountLocks = new HashMap<>();
    private final HashMap<String, Account> accounts = new HashMap<>();

    public Lock getLock(final String accountId) {
        return accountLocks.computeIfAbsent(accountId, (key) -> new ReentrantLock());
    }

    public Account getAccount(final String id) {
        return accounts.get(id); //TODO : Handle account not found with exception
    }

    public Account addAccount(final String id, double amount) {
        //todo : IDs should be generated inside the service (ideally by database)
        Account account = new Account(id, amount);
        this.accounts.put(id, account);
        return account;
    }
}
