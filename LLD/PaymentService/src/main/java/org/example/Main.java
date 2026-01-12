package org.example;

import org.example.handlers.CreditCardPaymentHandler;
import org.example.handlers.IPaymentHandler;
import org.example.handlers.UPIPaymentHandler;
import org.example.managers.CreditCardPaymentManager;
import org.example.managers.UPIPaymentManager;
import org.example.model.CreditCardPaymentTypeData;
import org.example.model.IPaymentTypeData;
import org.example.model.PaymentType;
import org.example.service.PaymentService;

import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        List<IPaymentHandler> paymentHandlers = List.of(new UPIPaymentHandler(new UPIPaymentManager()), new CreditCardPaymentHandler(new CreditCardPaymentManager()));
        PaymentService paymentService = new PaymentService(paymentHandlers);
        double amount = 100.0;
        IPaymentTypeData paymentTypeData = new CreditCardPaymentTypeData("4375510304233023", 2025, 9, 442, "Nikhil Neela");
        paymentService.pay(PaymentType.CREDIT_CARD, paymentTypeData, amount);
    }
}