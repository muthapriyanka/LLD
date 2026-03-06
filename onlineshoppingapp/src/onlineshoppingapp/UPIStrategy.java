package onlineshoppingapp;

public class UPIStrategy implements PaymentStrategy {
    private String upiId;

    public UPIStrategy(String upiId) {
        this.upiId = upiId;
    }

    @Override
    public boolean pay(double amount) {
        System.out.println("Processing UPI payment of $" + amount + " using UPI ID: " + upiId);
        // Here you would integrate with a real UPI payment gateway
        return true;
    }
    
}
