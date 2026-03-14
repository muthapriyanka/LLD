package pizzastore;

import java.math.BigDecimal;
import pizzastore.entities.Order;
import pizzastore.entities.Pizza;
import pizzastore.entities.Store;
import pizzastore.strategy.BuyOneGetOne;

public class PizzaDemo {
    public static void main(String[] args) {
        Store londonStore = new Store("London");
        londonStore.addBasePrice("Thin Crust", new BigDecimal("5.0"));
        londonStore.addToppingPrice("Cheese", new BigDecimal("1.0"));
        londonStore.addToppingPrice("Pepperoni", new BigDecimal("2.0"));
        londonStore.setDeal(new BuyOneGetOne());

        // Pizza 1 = 6.0
        Pizza cheapPizza = londonStore.buildPizza()
                .withBase("Thin Crust")
                .addTopping("Cheese")
                .build();

        // Pizza 2 = 8.0
        Pizza expensivePizza = londonStore.buildPizza()
                .withBase("Thin Crust")
                .addTopping("Cheese")
                .addTopping("Pepperoni")
                .build();

        System.out.println("Cheap Pizza Price     : " + cheapPizza.getPrice());
        System.out.println("Expensive Pizza Price : " + expensivePizza.getPrice());
        System.out.println();

        // Order 1: expensive first, cheap second
        Order order1 = new Order(londonStore);
        order1.addItem(expensivePizza);
        order1.addItem(cheapPizza);

        System.out.println("---- ORDER 1: Expensive pizza added first ----");
        System.out.println("Expected BOGO discount = cheaper pizza = 6.0");
        System.out.println("Final Total: " + order1.calculateTotal());
        System.out.println();

        // Order 2: cheap first, expensive second
        Order order2 = new Order(londonStore);
        order2.addItem(cheapPizza);
        order2.addItem(expensivePizza);

        System.out.println("---- ORDER 2: Cheap pizza added first ----");
        System.out.println("Expected BOGO discount = cheaper pizza = 6.0");
        System.out.println("Final Total: " + order2.calculateTotal());
        System.out.println();

    }
}