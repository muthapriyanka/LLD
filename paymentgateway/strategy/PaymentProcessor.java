package paymentgateway.strategy;

import paymentgateway.entities.PaymentRequest;
import paymentgateway.enums.PaymentStatus;

public interface PaymentProcessor {
    PaymentStatus process(PaymentRequest req);
}