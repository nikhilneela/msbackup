package org.splitwise.Managers;

import org.splitwise.models.ExpenseType;
import org.splitwise.models.Expense;
import org.splitwise.models.User;
import org.splitwise.repository.UserRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CommandLineManager {
    private String separator;
    private UserRepository userRepository;

    public CommandLineManager(String separator, UserRepository userRepository) {
        this.separator = separator;
        this.userRepository = userRepository;
    }

    public void execute(String commandLineInput) {
        final String[] inputParts = commandLineInput.split(separator);
        String commandName = inputParts[0];
        String[] commandParams = Arrays.copyOfRange(inputParts, 1, inputParts.length);
        switch (commandName) {
            case "EXPENSE" :
                Expense expense = createExpense(commandParams);
                break;
            case "SHOW" :
                break;
        }
    }

    //EXPENSE user1 1000 4 user1 user2 user3 user4 EQUAL
   //             0    1 2     3     4     5     6     7
    //EXPENSE user1 1250 2 user2 user3 EXACT 370 880
    public Expense createExpense(String[] commandParams) {
        Expense expense = new Expense();
        User fromUser = userRepository.getUser(commandParams[0]);
        int totalAmount = Integer.parseInt(commandParams[1]);
        int numberOfUsers = Integer.parseInt(commandParams[2]);

        List<User> friends = new ArrayList<>();
        for (int i = 0; i < numberOfUsers; i++) {
            friends.add(userRepository.getUser(commandParams[3 + i]));
        }

        ExpenseType type = ExpenseType.valueOf(commandParams[3 + numberOfUsers]);
        List<Integer> amounts = new ArrayList<>();
        if (type == ExpenseType.EXACT) {
            for (int i = 0; i < numberOfUsers; i++) {
                amounts.add(Integer.parseInt(commandParams[3 + numberOfUsers + 1 + i]));
            }
        }

        expense.setFrom(fromUser);
        expense.setAmounts(amounts);
        expense.setFriends(friends);
        expense.setExpenseType(type);
        expense.setTotalAmount(totalAmount);
        return null;
    }


}
