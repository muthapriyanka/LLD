package splitwise.src.strategy;

import java.util.List;

import splitwise.src.entities.Split;
import splitwise.src.entities.User;

public interface SplitStrategy {
    List<Split> calculateSplits(double totalAmount, User paidBy, List<User> participants, List<Double> splitValues);
}
