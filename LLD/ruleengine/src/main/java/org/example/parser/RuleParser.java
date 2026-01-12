package org.example.parser;

import lombok.AllArgsConstructor;
import org.example.model.*;
import org.example.strategy.IRuleStrategy;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
public class RuleParser {
    /*
      {
        rules : [
            {
                ruleType : "SELLER_MAX_THRESHOLD"
                sellerType : "restaurant",
                maxPrice : {
                    currencyType : USD,
                    value : 45
                }
            },
            {
                ruleType : "EXPENSE_NO_CHARGE",
                expenseType : "entertainment"
            },
            {
                ruleType : "TOTAL_EXPENSE_THRESHOLD",
                maxPrice : {
                    currencyType : USD,
                    value : 175
                }
            }
        ],
        expenses : [
            {
                expenseType : "FOOD",
                price : {
                    currencyType : USD,
                    value : 250
                },
                sellerType : "restaurant",
                sellerName : "ABC restaurant"
            },
            {
                expenseType : "ENTERTAINMENT",
                price : {
                    currencyType : USD,
                    value : 150
                },
                sellerType : "movie",
                sellerName : "pvr"
            }
        ]
      }
     */
    private final Map<RuleType, IRuleStrategy> ruleStrategiesMap;

    List<Rule> parseRules(final String json) {
        JSONObject jsonObject = new JSONObject(json);
        List<Rule> rules = new ArrayList<>();
        //rules
        JSONArray rulesArray = jsonObject.getJSONArray("rules");

        for (int i = 0; i < rulesArray.length(); ++i) {
            JSONObject ruleObject = rulesArray.getJSONObject(i);
            RuleType ruleType = RuleType.valueOf(ruleObject.getString("ruleType"));
            IRuleStrategy ruleStrategy = ruleStrategiesMap.get(ruleType);
            Rule rule = ruleStrategy.parseRule(rulesArray.getString(i));
            rules.add(rule);
        }
        return rules;
    }

    List<Expense> parseExpenses(final String json) {
        //expense is a straight forward object with fixed fields
        JSONObject jsonObject = new JSONObject(json);
        List<Expense> expenses = new ArrayList<>();
        //rules
        JSONArray expensesArray = jsonObject.getJSONArray("expenses");

        for (int i = 0; i < expensesArray.length(); ++i) {
            JSONObject expenseObject = expensesArray.getJSONObject(i);
            ExpenseType expenseType = ExpenseType.valueOf(expenseObject.getString("expenseType"));
            SellerType sellerType = SellerType.valueOf(expenseObject.getString("sellerType"));
            String sellerName = expenseObject.getString("sellerName");
            //price parsing
            Expense expense = new Expense("", expenseType, "", new Price(), new Seller("", "", sellerType));
            expenses.add(expense);
        }
        return expenses;
    }
}
