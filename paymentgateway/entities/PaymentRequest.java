package paymentgateway.entities;

import java.util.Map;
import paymentgateway.enums.PaymentMethodType;

public class PaymentRequest {
    private final String idempotencyKey;
    private final long amount;
    private final String currency;
    private final PaymentMethodType methodType;
    private final Map<String, String> details;

    public PaymentRequest(String idempotencyKey,
                          long amount,
                          String currency,
                          PaymentMethodType methodType,
                          Map<String, String> details) {
        this.idempotencyKey = idempotencyKey;
        this.amount = amount;
        this.currency = currency;
        this.methodType = methodType;
        this.details = details;
    }

    public String getIdempotencyKey() {
        return idempotencyKey;
    }
    
    public long getAmount() {
        return amount;
    }

    public String getCurrency() {
        return currency;
    }

    public PaymentMethodType getMethodType() {
        return methodType;
    }

    public Map<String, String> getDetails() {
        return details;
    }
}