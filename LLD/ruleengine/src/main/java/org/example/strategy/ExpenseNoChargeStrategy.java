package org.example.strategy;

import org.example.model.*;
import org.example.model.ruledata.ExpenseNoChargeData;
import org.example.model.ruledata.SellerMaxThresholdData;
import org.json.JSONObject;

public class ExpenseNoChargeStrategy implements IRuleStrategy {
    @Override
    public boolean evaluateRule(Rule rule, Expense expense) {
        ExpenseNoChargeData expenseNoChargeData = (ExpenseNoChargeData) rule.getData();
        return expenseNoChargeData.getType() == expense.getType() && expense.getPrice().isZero();
    }

    @Override
    public Rule parseRule(String json) {
        JSONObject jsonObject = new JSONObject(json);
        RuleType ruleType = RuleType.valueOf(jsonObject.getString("ruleType"));
        ExpenseType expenseType = ExpenseType.valueOf(jsonObject.getString("expenseType"));
        ExpenseNoChargeData expenseNoChargeData = new ExpenseNoChargeData(expenseType);
        return new Rule("", "", "", ruleType, expenseNoChargeData);
    }

    @Override
    public boolean doesSupport(RuleType type) {
        return type == RuleType.EXPENSE_NO_CHARGE;
    }
}
