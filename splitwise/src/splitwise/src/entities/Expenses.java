package splitwise.src.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import splitwise.src.strategy.SplitStrategy;

public class Expenses {
    int id;
    String description;
    double amount;
    User paidBy;
    SplitStrategy splitStrategy;
    List<Double> splitValues;

    List<User> participants;
    List<Split> splits; // List of how much each user owes for this expense
    
    public Expenses(String description, double amount, User paidBy, List<User> participants, SplitStrategy splitStrategy, List<Double> splitValues) {
        this.id = UUID.randomUUID().hashCode(); // Simple unique ID generation
        this.description = description;
        this.amount = amount;
        this.paidBy = paidBy;
        this.participants = participants;
        this.splitStrategy = splitStrategy;
        this.splitValues = splitValues;
        this.splits = new ArrayList<>();
    }

    public void addSplit(Split split) {
        splits.add(split);
    }

    public List<Split> calculateSplits() {
        return splitStrategy.calculateSplits(amount, paidBy, participants, splitValues);
    }


    
}
