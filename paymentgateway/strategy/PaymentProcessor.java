package paymentgateway.strategy;

import paymentgateway.entities.PaymentRequest;
import paymentgateway.enums.PaymentMethodType;
import paymentgateway.enums.PaymentStatus;

public interface PaymentProcessor {
    PaymentMethodType supports();
    PaymentStatus process(PaymentRequest req);
}