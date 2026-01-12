package org.example.handlers;

import lombok.AllArgsConstructor;
import org.example.model.IPaymentTypeData;
import org.example.model.PaymentType;
import org.example.model.User;

@AllArgsConstructor
public class PremiumUserCreditCardPaymentHandler implements IPaymentHandler {
    private final User user;

    @Override
    public boolean doesSupport(PaymentType paymentType) {

        return false;
    }

    @Override
    public boolean makePayment(double amount, IPaymentTypeData paymentTypeData) {
        return false;
    }
}
