package org.example.handlers;

import lombok.AllArgsConstructor;
import org.example.managers.CreditCardPaymentManager;
import org.example.model.CreditCardPaymentTypeData;
import org.example.model.IPaymentTypeData;
import org.example.model.PaymentType;

@AllArgsConstructor
public class CreditCardPaymentHandler implements IPaymentHandler {
    private final CreditCardPaymentManager creditCardPaymentManager;
    @Override
    public boolean doesSupport(PaymentType paymentType) {
        return paymentType == PaymentType.CREDIT_CARD;
    }

    @Override
    public boolean makePayment(double amount, IPaymentTypeData paymentTypeData) {
        CreditCardPaymentTypeData creditCardPaymentTypeData = (CreditCardPaymentTypeData) paymentTypeData;
        return creditCardPaymentManager.makePayment(
                creditCardPaymentTypeData.getCardNumber(),
                creditCardPaymentTypeData.getCvv(),
                creditCardPaymentTypeData.getExpiryYear(),
                creditCardPaymentTypeData.getExpiryMonth(),
                amount);
    }
}
