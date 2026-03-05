import java.util.ArrayList;
import java.util.List;

public class FoodDeliveryServiceDemo {
    public static void main(String[] args) {
        // Create some menu items
        MenuItem pizza = new MenuItem(1, "Pizza", 10, true);
        MenuItem burger = new MenuItem(2, "Burger", 5, true);
        MenuItem sushi = new MenuItem(3, "Sushi", 15, false);

        // Create a restaurant and add menu items
        Menu menu = new Menu(1, "Main Menu", new ArrayList<>());
        Restaurant restaurant = new Restaurant("Foodie's Paradise", new Address("New York", "NY", 10001, "456 Oak Ave", 40.7128, -74.0060), menu);
        restaurant.addToMenu(pizza);
        restaurant.addToMenu(burger);
        restaurant.addToMenu(sushi);

        FoodDeliveryService service = FoodDeliveryService.getInstance();
        service.addRestaurant(restaurant);
        service.addObserver(restaurant);

        // Create a customer
        User customer = new User(1, "John Doe", new Address("New York", "NY", 10002, "123 Main St", 40.7135, -74.0055));
        service.addUser(customer);
        service.addObserver(customer);

        // Place an order
        List<OrderLineItem> orderItems = new ArrayList<>();
        orderItems.add(new OrderLineItem(1, 2, pizza));
        orderItems.add(new OrderLineItem(1, 1, burger));
        Order order = service.placeOrder(customer, restaurant, orderItems);

        // Create delivery agents
        DeliveryAgent agent1 = new DeliveryAgent(new Address("New York", "NY", 10003, "789 Pine St", 40.7145, -74.0070), "Alice", 1, "555-0101");
        DeliveryAgent agent2 = new DeliveryAgent(new Address("New York", "NY", 10004, "321 Elm St", 40.7100, -74.0045), "Bob", 2, "555-0102");
        service.addDeliveryAgent(agent1);
        service.addDeliveryAgent(agent2);
        // Do not add agents to observers - they'll be added when assigned

        // Assign a delivery agent for the order
        service.assignDeliveryAgent(order);

        if (order.getDeliveryAgent() != null) {
            System.out.println("Assigned delivery agent: " + order.getDeliveryAgent().getName());
        } else {
            System.out.println("No available delivery agents at the moment.");
        }
    }
}
