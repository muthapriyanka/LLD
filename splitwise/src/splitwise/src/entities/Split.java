package splitwise.src.entities;

public class Split {
    private final User user;
    private final double amount;

    public Split(User user, double amount) {
        this.user = user;
        this.amount = amount;
    }

    public User getUser() { 
        return user; 
    }

    public double getAmount() { 
        return amount; 
    }
}

// This class represents how much a user owes for a particular expense. It is used in the SplitStrategy to calculate the splits for an expense.