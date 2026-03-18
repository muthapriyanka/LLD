package paymentgateway.observer;

import paymentgateway.entities.Payment;

public class CustomerNotifier implements PaymentNotifier {
    @Override
    public void onStatusChange(Payment payment) {
        System.out.println("Customer notified: Payment Status = " + payment.getStatus());
    }
}