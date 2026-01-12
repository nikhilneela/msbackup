package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CreditCardPaymentTypeData implements IPaymentTypeData {
    private final String cardNumber;
    private final int expiryYear;
    private final int expiryMonth;
    private final int cvv;
    private final String cardHolderName;
}
