import java.util.UUID;

public class Restaurant implements Observer {
    int Resturantid;
    String name;
    Address address;
    Menu menu;

    public Restaurant(String name, Address address, Menu menu) {
        Resturantid = UUID.randomUUID().hashCode(); // Generate a unique restaurant ID
        this.name = name;
        this.address = address;
        this.menu = menu;
    }
    public int getResturantid() {
        return Resturantid;
    }
    public String getName() {
        return name;
    }
    public Address getAddress() {
        return address;
    }
    public Menu getMenu() {
        return menu;
    }
    public void addToMenu(MenuItem item) { 
        this.menu.addItem(item); 
    }

    @Override
    public void update(Order order) {
        // Notify the restaurant about the order status update
        System.out.println("Restaurant " + name + " notified about order " + order.getOderid() + " status update.");
    }

    
}
