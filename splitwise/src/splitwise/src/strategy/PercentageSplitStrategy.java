package splitwise.src.strategy;

import java.util.*;
import splitwise.src.entities.*;

public class PercentageSplitStrategy implements SplitStrategy {
    
    @Override
    public List<Split> calculateSplits(double totalAmount, User paidBy, List<User> participants, List<Double> splitValues) {
        List<Split> splits = new ArrayList<>();
        for (int i = 0; i < participants.size(); i++) {
            double amountOwed = totalAmount * (splitValues.get(i) / 100.0);
            splits.add(new Split(participants.get(i), amountOwed));
        }
        return splits;
    }
    
}

// Suppose:
// Total expense = 1000
// Paid by = Alice
// Participants = Alice, Bob, Charlie
// And we want to split:

// Alice → 50%
// Bob → 30%
// Charlie → 20%

// Then:
// participants = [Alice, Bob, Charlie]
// splitValues = [50.0, 30.0, 20.0]

// That’s what splitValues is.
