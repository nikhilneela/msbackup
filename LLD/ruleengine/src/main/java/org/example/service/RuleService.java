package org.example.service;

import lombok.AllArgsConstructor;
import org.example.model.Expense;
import org.example.model.Rule;
import org.example.model.RuleType;
import org.example.strategy.IRuleStrategy;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class RuleService {
    private final List<IRuleStrategy> ruleStrategies;
    public List<Rule> evaluate(List<Rule> rules, List<Expense> expenses) {
        List<Rule> rulesThatDoNotMatch = new ArrayList<>();
        for (Expense expense : expenses) {
            for (Rule rule : rules) {
                boolean doesRuleMatch = false;
                for (IRuleStrategy strategy : ruleStrategies) {
                    if (strategy.evaluateRule(rule, expense)) {
                        doesRuleMatch = true;
                        break;
                    }
                }
                if (!doesRuleMatch) {
                    rulesThatDoNotMatch.add(rule);
                }
            }
        }
        return rulesThatDoNotMatch;
    }
}
