package paymentgateway;

import java.util.ArrayList;
import java.util.List;

import paymentgateway.entities.Payment;
import paymentgateway.entities.PaymentRequest;
import paymentgateway.enums.PaymentStatus;
import paymentgateway.observer.PaymentNotifier;
import paymentgateway.strategy.PaymentProcessor;

public class PaymentService {
    private final List<PaymentNotifier> observers = new ArrayList<>();
    private final int maxRetries = 3;
    private final PaymentProcessorFactory factory = new PaymentProcessorFactory();

    public void addObserver(PaymentNotifier observer) {
        observers.add(observer);
    }

    private void notifyObservers(Payment payment) {
        for (PaymentNotifier observer : observers) {
            observer.onStatusChange(payment);
        }
    }

    public void pay(PaymentRequest req) {
        Payment payment = new Payment(req.getReqId());
        updateStatus(payment, PaymentStatus.PROCESSING);

        PaymentProcessor processor = factory.getPaymentProcessor(req.getMethodType());

        for (int attempt = 1; attempt <= maxRetries; attempt++) {
            PaymentStatus status = processor.process(req);

            if (status == PaymentStatus.COMPLETED) {
                updateStatus(payment, PaymentStatus.COMPLETED);
                return;
            }
        }

        updateStatus(payment, PaymentStatus.FAILED);
    }

    private void updateStatus(Payment payment, PaymentStatus newStatus) {
        payment.setStatus(newStatus);
        notifyObservers(payment);
    }
}