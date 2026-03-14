package pizzastore.strategy;

import java.math.BigDecimal;
import pizzastore.entities.Drink;
import pizzastore.entities.Order;
import pizzastore.entities.OrderItem;
import pizzastore.entities.Pizza;

public class FreeDrinkWithPizza implements Deal {
    @Override
    public BigDecimal calculateDiscount(Order order) {
        boolean hasPizza = false;
         for (OrderItem item : order.getItems()) {
            if (item instanceof Pizza) {
                 hasPizza = true;
                break;
            }
        }

        if (hasPizza) {
            // Assuming the free drink is the cheapest one in the order
            BigDecimal cheapestDrinkPrice = order.getItems().stream() // This converts the list of items in the order into a stream so we can process them using functional operations.
                .filter(item -> item instanceof Drink)
                .map(OrderItem::getPrice)
                .min(BigDecimal::compareTo)
                .orElse(BigDecimal.ZERO);
            
            return cheapestDrinkPrice; // Discount equals the price of the free drink
        }
        
        return BigDecimal.ZERO; // No discount if conditions are not met
    }
}

// 2️⃣ order.getItems().stream()

// This converts the list of items in the order into a stream so we can process them using functional operations.

// Example order:

// [Pizza, Drink $3, Drink $5]
// 3️⃣ .filter(item -> item instanceof Drink)

// We only care about drinks, because the deal is about free drink with pizza.

// After filtering:

// [Drink $3, Drink $5]

// Pizza is removed.

// 4️⃣ .map(OrderItem::getPrice)

// Now we transform each drink object into its price.

// Example:

// Drink $3 → 3
// Drink $5 → 5

// Now the stream contains:

// [3, 5]

// (type = BigDecimal)

// 5️⃣ .min(BigDecimal::compareTo)

// This finds the minimum value in the stream.

// Example:

// min(3, 5) = 3

// So:

// cheapestDrinkPrice = 3

// Note: BigDecimal::compareTo is used because BigDecimal doesn’t use normal < comparisons.

// 6️⃣ .orElse(BigDecimal.ZERO)

// This handles the case where no drinks exist.

// Example:

// Order:

// Pizza
// Pizza

// After filtering drinks:

// []

// Stream is empty.

// So:

// min() → empty Optional

// Then:

// orElse(BigDecimal.ZERO)

// Result:

// cheapestDrinkPrice = 0

// Meaning no drink → no free drink discount.

// 7️⃣ return cheapestDrinkPrice

// This means:

// The discount equals the price of the cheapest drink.

// Example:

// Order:

// Pizza $12
// Drink $3
// Drink $5

// Total before discount:

// 12 + 3 + 5 = 20

// Discount:

// cheapestDrinkPrice = 3

// Final price:

// 20 - 3 = 17

// So effectively:

// $3 drink is free