package splitwise.src.entities;

public class Transaction {
    private final User from;
    private final User to;
    private final double amount;

    public Transaction(User from, User to, double amount) {
        this.from = from;
        this.to = to;
        this.amount = amount;
    }

    @Override
    public String toString() {
        return from.getName() + " should pay " + to.getName() + " $" + String.format("%.2f", amount);
    }
}


// Even if Splitwise does not process payment:

// We still need to record:

// Bob paid Alice 500

// Without Transaction class:

// How do you reduce balances?

// How do you maintain payment history?

// How do you support partial settlement?

//split = expense division
//transaction = payment settlement