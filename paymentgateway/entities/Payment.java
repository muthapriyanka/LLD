package paymentgateway.entities;

import paymentgateway.enums.PaymentStatus;

public class Payment {
    private final String paymentId;
    private volatile PaymentStatus status;

    public Payment(String paymentId) {
        this.paymentId = paymentId;
        this.status = PaymentStatus.CREATED;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public PaymentStatus getStatus() {
        return status;
    }

    public void setStatus(PaymentStatus status) {
        this.status = status;
    }
}

// PaymentRequest is meant to be:

// immutable input from client

// If you add status to it, you’re mixing input + system state, which creates problems:

// 1. Violates separation of concerns
// Request → comes from outside (client)
// Status → managed by system
