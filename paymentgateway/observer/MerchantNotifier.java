package paymentgateway.observer;

import paymentgateway.entities.Payment;

public class MerchantNotifier implements PaymentNotifier {
    @Override
    public void onStatusChange(Payment payment) {
        System.out.println("Merchant notified: Payment Status = " + payment.getStatus());
    }
}