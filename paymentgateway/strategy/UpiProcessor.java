package paymentgateway.strategy;

import paymentgateway.entities.PaymentRequest;
import paymentgateway.enums.PaymentMethodType;
import paymentgateway.enums.PaymentStatus;

public class UpiProcessor implements PaymentProcessor {
    @Override
    public PaymentMethodType supports() {
        return PaymentMethodType.UPI;
    }

    @Override
    public PaymentStatus process(PaymentRequest req) {
        return PaymentStatus.COMPLETED;
    }
}