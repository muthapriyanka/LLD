package splitwise.src.strategy;

import java.util.*;
import splitwise.src.entities.Split;
import splitwise.src.entities.User;

public class EqualSplitStrategy implements SplitStrategy {

    @Override
    public List<Split> calculateSplits(double totalAmount, User paidBy, List<User> participants, List<Double> splitValues) {
        List<Split> splits = new ArrayList<>();
        double amountPerPerson = totalAmount / participants.size();
        for (User participant : participants) {
            splits.add(new Split(participant, amountPerPerson));
        }
        return splits;
    }
}

