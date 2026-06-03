package splitwise.src.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Group {
    private final String id;
    private final String name;
    private final List<User> members;
    private final List<Expenses> expenses;

    public Group(String name, List<User> members) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.members = members;
        this.expenses = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<User> getMembers() {
        return new ArrayList<>(members);
    }

    public void addExpense(Expenses expense) {
        expenses.add(expense);
    }

    public List<Expenses> getExpenses() {
        return new ArrayList<>(expenses);
    }

}
