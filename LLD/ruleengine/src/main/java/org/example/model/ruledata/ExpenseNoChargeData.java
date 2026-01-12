package org.example.model.ruledata;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.example.model.ExpenseType;

@AllArgsConstructor
@Getter
public class ExpenseNoChargeData implements IRuleData {
    private final ExpenseType type;
}
