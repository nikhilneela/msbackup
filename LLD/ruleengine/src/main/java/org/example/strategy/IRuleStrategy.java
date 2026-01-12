package org.example.strategy;

import org.example.model.Expense;
import org.example.model.Rule;
import org.example.model.RuleType;

public interface IRuleStrategy {
    boolean evaluateRule(Rule rule, Expense expense);
    Rule parseRule(String json);
    boolean doesSupport(RuleType type);
}
