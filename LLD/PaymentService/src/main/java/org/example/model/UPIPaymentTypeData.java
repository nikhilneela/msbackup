package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UPIPaymentTypeData implements IPaymentTypeData {
    private String upiId;
}
