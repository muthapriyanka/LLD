package onlineshoppingapp;

import java.util.UUID;

public class User {
    String name;
    String email;
    String userId;
    Address address;
    Account account;

    public User(String name, String email, Address address) {
        this.name = name;
        this.email = email;
        this.userId = UUID.randomUUID().toString();
        this.account = new Account(name, "password"); // In real system, password should be hashed and securely stored
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public Account getAccount() {
        return account;
    }

    public String getUserId() {
        return userId;
    }
    public Address getShippingAddress() {
        return address;
    }


}
