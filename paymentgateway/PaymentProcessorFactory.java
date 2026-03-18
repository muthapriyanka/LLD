package paymentgateway;

import paymentgateway.enums.PaymentMethodType;
import paymentgateway.strategy.CardProcessor;
import paymentgateway.strategy.PaymentProcessor;
import paymentgateway.strategy.PaypalProcessor;
import paymentgateway.strategy.UpiProcessor;

public class PaymentProcessorFactory {

    public PaymentProcessor getPaymentProcessor(PaymentMethodType type) {
        switch (type) {
            case CARD:
                return new CardProcessor();
            case PAYPAL:
                return new PaypalProcessor();
            case UPI:
                return new UpiProcessor();
            default:
                throw new IllegalArgumentException("Unsupported payment method: " + type);
        }
    }
}