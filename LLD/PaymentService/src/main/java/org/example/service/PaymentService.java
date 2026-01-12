package org.example.service;

import lombok.AllArgsConstructor;
import org.example.handlers.IPaymentHandler;
import org.example.model.IPaymentTypeData;
import org.example.model.PaymentType;

import java.util.List;
@AllArgsConstructor
public class PaymentService {
    private final List<IPaymentHandler> paymentHandlers;

    public boolean pay(PaymentType paymentType, IPaymentTypeData paymentTypeData, double amount) {
        for (IPaymentHandler paymentHandler : paymentHandlers) {
            if (paymentHandler.doesSupport(paymentType)) {
                return paymentHandler.makePayment(amount, paymentTypeData);
            }
        }
        throw new RuntimeException("Unsupported payment type");
    }
}
