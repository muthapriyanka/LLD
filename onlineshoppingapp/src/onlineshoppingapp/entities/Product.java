package onlineshoppingapp.entities;

import java.util.UUID;

public class Product {
    String productId;
    String name;
    String description;
    double price;
    ProductCategory category;
    int availableQuantity;

    public Product(String name, String description, double price, ProductCategory category, int availableQuantity) {
        this.productId = UUID.randomUUID().toString();
        this.name = name;
        this.description = description;
        this.price = price;
        this.category = category;
        this.availableQuantity = availableQuantity;
    }

    public String getProductId() {
        return productId;
    }

    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
    }
    public double getPrice() {
        return price;
    }
    public ProductCategory getCategory() {
        return category;
    }

    public int getAvailableQuantity() {
        return availableQuantity;
    }

    public boolean isAvailable(int quantity) {
        return quantity > 0 && availableQuantity >= quantity;
    }

    public void reduceStock(int quantity) {
        if (!isAvailable(quantity)) {
            throw new IllegalArgumentException("Not enough stock available for " + name);
        }
        availableQuantity -= quantity;
    }

    public void increaseStock(int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be positive");
        }
        availableQuantity += quantity;
    }
}
