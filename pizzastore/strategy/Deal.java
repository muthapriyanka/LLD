package pizzastore.strategy;

import java.math.BigDecimal;
import pizzastore.entities.Order;

public interface Deal {
    BigDecimal calculateDiscount(Order order);
}

