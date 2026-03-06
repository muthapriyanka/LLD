package onlineshoppingapp;

import java.util.UUID;

public class Product {
    String productId;
    String name;
    String description;
    double price;
    ProductCategory category;

    public Product(String name, String description, double price, ProductCategory category) {
        this.productId = UUID.randomUUID().toString();
        this.name = name;
        this.description = description;
        this.price = price;
        this.category = category;
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
}
