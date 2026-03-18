package paymentgateway.observer;

import paymentgateway.entities.Payment;

public interface PaymentNotifier {
    void onStatusChange(Payment payment);
}

