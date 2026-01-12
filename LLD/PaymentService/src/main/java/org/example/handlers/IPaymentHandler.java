package org.example.handlers;

import org.example.model.IPaymentTypeData;
import org.example.model.PaymentType;

public interface IPaymentHandler {
    boolean doesSupport(PaymentType paymentType);
    boolean makePayment(double amount, IPaymentTypeData paymentTypeData);
}
