package org.example.strategy;

import org.example.model.*;
import org.example.model.ruledata.SellerMaxThresholdData;
import org.json.JSONObject;

public class SellerMaxThresholdRuleStrategy implements IRuleStrategy {

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
    @Override
    public boolean evaluateRule(Rule rule, Expense expense) {
        SellerMaxThresholdData sellerMaxThresholdData = (SellerMaxThresholdData) rule.getData();
        return expense.getSeller().getType() == sellerMaxThresholdData.getType() && expense.getPrice().isLesserThan(sellerMaxThresholdData.getMaxPrice());
    }

    @Override
    public Rule parseRule(String json) {
        JSONObject jsonObject = new JSONObject(json);
        RuleType ruleType = RuleType.valueOf(jsonObject.getString("ruleType"));
        SellerType sellerType = SellerType.valueOf(jsonObject.getString("sellerType"));
        //parse price from price data
        Price price = new Price();
        SellerMaxThresholdData sellerMaxThresholdData = new SellerMaxThresholdData(sellerType, price);
        return new Rule("", "", "", ruleType, sellerMaxThresholdData);
    }

    @Override
    public boolean doesSupport(RuleType type) {
        return type == RuleType.SELLER_MAX_THRESHOLD;
    }
}
