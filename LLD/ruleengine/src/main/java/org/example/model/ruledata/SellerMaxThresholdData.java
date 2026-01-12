package org.example.model.ruledata;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.example.model.Price;
import org.example.model.SellerType;

@AllArgsConstructor
@Getter
public class SellerMaxThresholdData implements IRuleData {
    private final SellerType type;
    private final Price maxPrice;
}
