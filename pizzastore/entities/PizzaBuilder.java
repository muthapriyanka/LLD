package pizzastore.entities;

import java.util.ArrayList;
import java.util.List;

public class PizzaBuilder {
    private Store store;  
    private Base base;
    private List<Topping> toppings;

        public PizzaBuilder(Store store) {  // Every builder is tied to one store.
        this.store = store;
        this.toppings = new ArrayList<>();
    }

    public PizzaBuilder withBase(String baseName) {
        this.base = store.createBase(baseName);
        return this;
    }

    public PizzaBuilder addTopping(String toppingName) {
            this.toppings.add(store.createTopping(toppingName));
            return this;
        }

    public Pizza build() {
        if (base == null) { //// It prevents invalid pizza creation
            throw new IllegalStateException("Pizza base must be selected");
        }
            Pizza pizza = new Pizza(base);
            for (Topping topping : toppings) {
                pizza.addTopping(topping);
            }
            return pizza;
    }
}

