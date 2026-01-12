package org.example.handlers;

import lombok.AllArgsConstructor;
import org.example.model.IPaymentTypeData;
import org.example.model.PaymentType;
import org.example.managers.UPIPaymentManager;
import org.example.model.UPIPaymentTypeData;

@AllArgsConstructor
public class UPIPaymentHandler implements IPaymentHandler {
    private final UPIPaymentManager upiPaymentManager;
    @Override
    public boolean doesSupport(PaymentType paymentType) {
        return PaymentType.UPI == paymentType;
    }

    @Override
    public boolean makePayment(double amount, IPaymentTypeData paymentTypeData) {
        UPIPaymentTypeData upiPaymentTypeData = (UPIPaymentTypeData) paymentTypeData;
        return upiPaymentManager.makePayment(upiPaymentTypeData.getUpiId(), amount);
    }
}
