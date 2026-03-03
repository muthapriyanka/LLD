public class UPIPayment implements Payment {
    @Override
    public boolean processPayment(double amount) {
        System.out.println("Processing UPI payment of amount: " + amount);
        return true;
    }
    
}
