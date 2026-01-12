package org.splitwise.Managers;

import org.splitwise.models.Expense;
import org.splitwise.models.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExpenseManager {
    private List<User> users;
    private Map<String, Map<String, Integer>> records;

    private static final String NOBALNCES = "NOBALANCES";

    public ExpenseManager() {
        this.users = new ArrayList<>();
        this.records = new HashMap<>();
    }

    public void addUser(User user) {
        this.users.add(user);
    }

    public void recordTransaction(Expense transaction) {
        //check if source user and target users are present in the system of users
        //we do not want to add records of transaction who are not registered in the system
        //Assume transaction is validated before-hand
        User fromUser = transaction.getFrom();
        List<User> targetUsers = transaction.getFriends();
        List<Integer> amounts = transaction.getAmounts();
        for (int i = 0; i < targetUsers.size(); ++i) {
            User targetUser = targetUsers.get(0);

            if (!fromUser.equals(targetUser)) {
                Map<String, Integer> duesRecord = records.getOrDefault(targetUser.getUserId(), new HashMap<>());

                int alreadyDue = duesRecord.getOrDefault(fromUser.getUserId(), 0);
                duesRecord.put(fromUser.getUserId(), alreadyDue + amounts.get(i));
                records.put(targetUser.getUserId(), duesRecord);
            }
        }
    }

    public List<String> showBalances(User user) {
        List<String> balances = new ArrayList<>();

        if (!records.containsKey(user.getUserId())) {
            balances.add(NOBALNCES);
            return balances;
        }

        Map<String, Integer> duesRecord = records.get(user.getUserId());

        for (Map.Entry<String, Integer> entry : duesRecord.entrySet()) {
            balances.add(String.format("{0} owes {1}: {2}", user.getUserId(), entry.getKey(), entry.getValue()));
        }
        return balances;
    }


    public List<String> showAllBalances() {
        List<String> balances = new ArrayList<>();
        return balances;
    }


}
