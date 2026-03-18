package paymentgateway.entities;

import java.util.Map;
import paymentgateway.enums.PaymentMethodType;

public class PaymentRequest {
    private final String reqId;
    private final long amountMinor;
    private final String currency;
    private final PaymentMethodType methodType;
    private final Map<String, String> details;

    public PaymentRequest(String reqId,
                          long amountMinor,
                          String currency,
                          PaymentMethodType methodType,
                          Map<String, String> details) {
        this.reqId = reqId;
        this.amountMinor = amountMinor;
        this.currency = currency;
        this.methodType = methodType;
        this.details = details;
    }

    public String getReqId() {
        return reqId;
    }

    public long getAmountMinor() {
        return amountMinor;
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