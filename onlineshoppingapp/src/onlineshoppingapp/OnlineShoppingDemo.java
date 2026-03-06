package onlineshoppingapp;

public class OnlineShoppingDemo {
    public static void main(String[] args) {
        OnlineShopping onlineShopping = OnlineShopping.getInstance();

        Product product1 = new Product("Laptop", "High performance laptop", 1000.0, ProductCategory.Electronics);
        Product product2 = new Product("T-Shirt", "Cotton t-shirt", 20.0, ProductCategory.Clothing);
        Product product3 = new Product("Vacuum Cleaner", "Powerful vacuum", 150.0, ProductCategory.HomeAppliances);
        User user1 = new User("Alice", "alice@example.com", new Address("123 Main St", "City", "State", "Zip"));
        User user2 = new User("Bob", "bob@example.com", new Address("456 Oak Ave", "City", "State", "Zip"));
        onlineShopping.addProduct(product1);
        onlineShopping.addProduct(product2);
        onlineShopping.addProduct(product3);
        onlineShopping.addUser(user1);
        onlineShopping.addUser(user2);

        user1.getAccount().getCart().addItem(product1, 1);
        user1.getAccount().getCart().addItem(product2, 2);
        user2.getAccount().getCart().addItem(product3, 1);
        PaymentStrategy paymentStrategy = new CashStrategy();
        onlineShopping.setPaymentStrategy(paymentStrategy);
        Order order1 = onlineShopping.placeOrder(user1);
        Order order2 = onlineShopping.placeOrder(user2);
        System.out.println("Order 1: " + order1.getOrderId() + ", Total: $" + order1.getTotalAmount() + ", Status: " + order1.getStatus());
        System.out.println("Order 2: " + order2.getOrderId() + ", Total: $" + order2.getTotalAmount() + ", Status: " + order2.getStatus());
    }
}
