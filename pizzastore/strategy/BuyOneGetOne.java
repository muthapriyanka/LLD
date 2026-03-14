package pizzastore.strategy;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import pizzastore.entities.Order;
import pizzastore.entities.Pizza;

public class BuyOneGetOne implements Deal {
    @Override
    public BigDecimal calculateDiscount(Order order) {
        // Calculate the discount for "Buy One Get One Free" deal
        // For simplicity, let's assume it applies to pizzas only
       List<Pizza> pizzas = order.getItems().stream()
            .filter(item -> item instanceof Pizza)
            .map(item -> (Pizza) item)
            .sorted(Comparator.comparing(Pizza::getPrice))
            .collect(Collectors.toList());

        BigDecimal discount = BigDecimal.ZERO;

        for (int i = 0; i + 1 < pizzas.size(); i += 2) {
            discount = discount.add(pizzas.get(i).getPrice());
        }

        return discount;
    }
    
}
