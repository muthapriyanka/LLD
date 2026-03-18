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