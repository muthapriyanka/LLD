package atm.entities;

public class Transaction {
    private final long transactionId;
    private final OperationType operationType;
    private final double amount;
    private final TransactionStatus status;
    private final String message;

    public Transaction(long transactionId, OperationType operationType, double amount,
                       TransactionStatus status, String message) {
        this.transactionId = transactionId;
        this.operationType = operationType;
        this.amount = amount;
        this.status = status;
        this.message = message;
    }

    public long getTransactionId() {
        return transactionId;
    }

    public OperationType getOperationType() {
        return operationType;
    }

    public double getAmount() {
        return amount;
    }

    public TransactionStatus getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "transactionId=" + transactionId +
                ", operationType=" + operationType +
                ", amount=" + amount +
                ", status=" + status +
                ", message='" + message + '\'' +
                '}';
    }
}
