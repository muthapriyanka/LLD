package splitwise.src;

import java.util.*;
import splitwise.src.entities.*;
import splitwise.src.strategy.PercentageSplitStrategy;

public class SplitwiseServiceDemo {

    public static void main(String[] args) {
        SplitwiseService instance = SplitwiseService.getInstance();
        // Create users
        User alice = instance.addUser("Alice", "alice@example.com");
        User bob = instance.addUser("Bob", "bob@example.com");
        User charlie = instance.addUser("Charlie", "charlie@example.com");

        // Create group
        List<User> groupMembers = Arrays.asList(alice, bob, charlie);
        Group tripGroup = instance.addGroup("Trip to Bali", groupMembers);

        // Set split strategy
        instance.setSplitStrategy(new PercentageSplitStrategy());
        // Add expense
        instance.addExpense("Hotel", 1000, alice, groupMembers, Arrays.asList(50.0, 30.0, 20.0));

        // Show balances
        alice.getBalanceSheet().showBalances();
        bob.getBalanceSheet().showBalances();
        charlie.getBalanceSheet().showBalances();


        // Record a transaction (Bob pays Alice $500)
        instance.settleup(charlie, alice, 10);
        charlie.getBalanceSheet().showBalances();
        alice.getBalanceSheet().showBalances();

    }
}
