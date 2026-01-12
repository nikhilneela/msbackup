package org.splitwise.models;

import java.util.List;

public class Expense {
    private User from;
    private List<User> friends;
    private int totalAmount;
    private ExpenseType expenseType;
    private List<Integer> amounts;


    public User getFrom() {
        return from;
    }

    public List<User> getFriends() {
        return friends;
    }

    public int getTotalAmount() {
        return totalAmount;
    }

    public ExpenseType getExpenseType() {
        return expenseType;
    }

    public List<Integer> getAmounts() {
        return amounts;
    }

    public void setFrom(User from) {
        this.from = from;
    }

    public void setFriends(List<User> friends) {
        this.friends = friends;
    }

    public void setTotalAmount(int totalAmount) {
        this.totalAmount = totalAmount;
    }

    public void setExpenseType(ExpenseType expenseType) {
        this.expenseType = expenseType;
    }

    public void setAmounts(List<Integer> amounts) {
        this.amounts = amounts;
    }
}
