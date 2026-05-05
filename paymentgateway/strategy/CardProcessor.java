package paymentgateway.strategy;

import paymentgateway.entities.PaymentRequest;
import paymentgateway.enums.PaymentMethodType;
import paymentgateway.enums.PaymentStatus;

public class CardProcessor implements PaymentProcessor {

    @Override
    public PaymentStatus process(PaymentRequest req) {
        return PaymentStatus.COMPLETED;
    }
}