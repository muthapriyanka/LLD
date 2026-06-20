package pizzastore.entities;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import pizzastore.strategy.Deal;

public class Store {
    private String name;
    private Map<String, BigDecimal> basePrices;
    private Map<String, BigDecimal> toppingPrices;
    private Map<String, BigDecimal> drinkPrices;
    private Deal deal;

    public Store(String name) {
        this.name = name;
        this.basePrices = new HashMap<>();
        this.toppingPrices = new HashMap<>();
        this.drinkPrices = new HashMap<>();
    }

    public String getName() {
        return name;
    }

    public void addBasePrice(String name, BigDecimal price) {
        this.basePrices.put(name, price);
    }

    public void addToppingPrice(String name, BigDecimal price) {
        this.toppingPrices.put(name, price);
    }
    public void addDrinkPrice(String name, BigDecimal price) {
        this.drinkPrices.put(name, price);
    }

    public Base createBase(String name) {
        if (!basePrices.containsKey(name)) {
            throw new IllegalArgumentException("Base '" + name + "' not found in store price list.");
        }
        return new Base(name, basePrices.get(name));
    }

    public Topping createTopping(String name) {
        if (!toppingPrices.containsKey(name)) {
            throw new IllegalArgumentException("Topping '" + name + "' not found in store price list.");
        }
        return new Topping(name, toppingPrices.get(name));
    }

    public Drink createDrink(String name) {
        if (!drinkPrices.containsKey(name)) {
            throw new IllegalArgumentException("Drink '" + name + "' not found in store price list.");
        }
        return new Drink(name, drinkPrices.get(name));
    }

    public void setDeal(Deal deal) {
        this.deal = deal;
    }

    public Deal getDeal() {
        return deal;
}
    
    public PizzaBuilder buildPizza() { 
        return new PizzaBuilder(this);
    }

}

//factory method for creating Topping and Drink can be added similarly if needed.
