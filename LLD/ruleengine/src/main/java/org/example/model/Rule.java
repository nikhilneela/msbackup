package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.example.model.ruledata.IRuleData;

@AllArgsConstructor
@Getter
public class Rule {
    private final String id;
    private final String name;
    private final String description;
    private final RuleType type;
    private final IRuleData data;
}
