package paymentgateway;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import paymentgateway.entities.Payment;
import paymentgateway.entities.PaymentRequest;
import paymentgateway.enums.PaymentStatus;
import paymentgateway.observer.PaymentNotifier;
import paymentgateway.strategy.PaymentProcessor;

public class PaymentService {
    private final List<PaymentNotifier> observers = new ArrayList<>();
    private final int maxRetries = 3;
    private final PaymentProcessorFactory factory = new PaymentProcessorFactory();
    private final Map<String, Payment> paymentsByRequestId = new HashMap<>();

    public void addObserver(PaymentNotifier observer) {
        observers.add(observer);
    }

    private void notifyObservers(Payment payment) {
        for (PaymentNotifier observer : observers) {
            observer.onStatusChange(payment);
        }
    }

    public Payment pay(PaymentRequest req) {

         // Step 1: Idempotency check
        Payment existingPayment = paymentsByRequestId.get(req.getIdempotencyKey());
        if (existingPayment != null) {
            return existingPayment;
        }

        Payment payment = new Payment(req.getIdempotencyKey());
        paymentsByRequestId.put(req.getIdempotencyKey(), payment);

        updateStatus(payment, PaymentStatus.PROCESSING);

        PaymentProcessor processor = factory.getPaymentProcessor(req.getMethodType());

        for (int attempt = 1; attempt <= maxRetries; attempt++) {
            PaymentStatus status = processor.process(req);

            if (status == PaymentStatus.COMPLETED) {
                updateStatus(payment, PaymentStatus.COMPLETED);
                return payment;
            }
        }

        updateStatus(payment, PaymentStatus.FAILED);
        return payment;
    }

    private void updateStatus(Payment payment, PaymentStatus newStatus) {
        payment.setStatus(newStatus);
        notifyObservers(payment);
    }
}


// If asked:
// How does idempotency work?
// Say:
// The client generates a unique idempotency key (reqId) per payment attempt. If the request fails or times out, the client retries using the same reqId. On the server side, 
// I store processed payments keyed by reqId and return the existing result for duplicate requests, preventing double charges.


// 1. Client retry (idempotency case)

// This is when:

// Client sends request → times out
// Client retries with same idempotency key (reqId)
// Request 1 (reqId = A) → processing started
// Request 2 (reqId = A) → retry from client

// In this case:

// if (existingPayment != null) {
//     return existingPayment;
// }

// ✅ Correct — you should return immediately
// ❌ You should NOT reprocess (to avoid double charge)

// 2. Internal retry (your for loop)

// This is inside a single request execution:

// for (int attempt = 1; attempt <= maxRetries; attempt++) {
//     PaymentStatus status = processor.process(req);
// }

// This handles:

// Bank timeout
// Network glitch
// Temporary failure

// So:

// ONE request → multiple attempts internally
// Key difference (this is what interviewer wants)
// Type	Who retries	Same reqId?	Loop runs?
// Client retry	Frontend	✅ Yes	❌ No (returns early)
// Internal retry	Your service	N/A	✅ Yes
// Timeline example
// First request
// reqId = A
// → no existing payment
// → create payment
// → run retry loop (bank failures)
// → eventually COMPLETED
// → stored in map
// Client retry (same reqId = A)
// → found in map
// → return existing payment
// → NO retry loop
// Why both are needed

// Without internal retry:

// transient failures → immediate failure ❌

// Without idempotency:

// duplicate requests → double charge ❌

// You need both for a real payment system.

// Interview answer (very strong)

// There are two types of retries:

// Client retries handled via idempotency key — I return existing payment to avoid duplicate processing.
// Internal retries handled via a retry loop — used for transient failures like network or bank issues.

// These solve different problems and both are required.