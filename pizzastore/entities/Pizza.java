package pizzastore.entities;

import java.math.BigDecimal;
import java.util.*;

public class Pizza implements OrderItem {
    private Base base;
    private List<Topping> toppings;

    public Pizza(Base base) {
        this.base = base;
        this.toppings = new ArrayList<>();
    }

    public void addTopping(Topping topping) {
        toppings.add(topping);
    }


    @Override
    public BigDecimal getPrice() {
        BigDecimal totalPrice = base.getPrice();
        for (Topping topping : toppings) {
            totalPrice = totalPrice.add(topping.getPrice());
        }
        return totalPrice;
    }
    
}
