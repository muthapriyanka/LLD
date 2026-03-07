package splitwise.src;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import splitwise.src.entities.Expenses;
import splitwise.src.entities.Group;
import splitwise.src.entities.Split;
import splitwise.src.entities.User;
import splitwise.src.strategy.SplitStrategy;

public class SplitwiseService {
    Map<String, User> users = new HashMap<>();
    List<Group> groups;
    SplitStrategy splitStrategy;

    private static SplitwiseService instance;

    private SplitwiseService() {
        this.groups = new ArrayList<>();
    }

    public static SplitwiseService getInstance() {
        if (instance == null) {
            instance = new SplitwiseService();
        }
        return instance;
    }

    public User addUser(String name, String email) {
        User user = new User(name, email);
        users.put(user.getId(), user);
        return user;
    }

    public Group addGroup(String name, List<User> members) {
        Group group = new Group(name, members);
        groups.add(group);
        return group;
    }

    public void setSplitStrategy(SplitStrategy splitStrategy) {
        this.splitStrategy = splitStrategy;
    }

    public SplitStrategy getSplitStrategy() {
        return splitStrategy;
    }

    public void addExpense(String description, double amount, User paidBy, List<User> participants, List<Double> splitValues) {
        Expenses expense = new Expenses(description, amount, paidBy, participants, splitStrategy, splitValues);
        List<Split> splits = expense.calculateSplits();

        for (Split split : splits) {
            expense.addSplit(split);
        }
        // Update balance sheets for all participants based on the splits
        for (Split split : splits) {
            User participant = split.getUser();
            double owedAmount = split.getAmount();
            if (!participant.equals(paidBy)) {
                // Participant owes money to the payer
                participant.getBalanceSheet().updateBalance(paidBy, -owedAmount);
                paidBy.getBalanceSheet().updateBalance(participant, owedAmount);
            }
        }
    }

    
    public synchronized void settleup(User payer, User payee, double amount) {
        System.out.println(payer.getName() + " is settling up " + amount + " with " + payee.getName());
        // payer owes payee: decrease the amount owed on both sheets
        // on payer's sheet a negative balance means they owe the payee,
        // so adding the payment amount moves the balance toward zero.
        payer.getBalanceSheet().updateBalance(payee, amount);
        // on payee's sheet a positive balance means payer owes them,
        // so subtracting the payment reduces that amount.
        payee.getBalanceSheet().updateBalance(payer, -amount);
    }
}
