package paymentgateway;

import java.util.HashMap;
import java.util.Map;

import paymentgateway.entities.PaymentRequest;
import paymentgateway.enums.PaymentMethodType;
import paymentgateway.observer.CustomerNotifier;
import paymentgateway.observer.MerchantNotifier;

public class PaymentDemo {
    public static void main(String[] args) {
        PaymentService service = new PaymentService();

        service.addObserver(new CustomerNotifier());
        service.addObserver(new MerchantNotifier());

        Map<String, String> details = new HashMap<>();
        details.put("token", "tok_abc123");

        PaymentRequest req = new PaymentRequest(
                "pay_1",
                50000,
                "INR",
                PaymentMethodType.CARD,
                details
        );

        service.pay(req);
    }
}