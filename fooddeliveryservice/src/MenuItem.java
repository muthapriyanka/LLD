

public class MenuItem {
    int id;
    String name;
    int price;
    boolean isAvailable;

    public MenuItem(int id, String name, int price, boolean isAvailable) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.isAvailable = isAvailable;
    }
    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public int getPrice() {
        return price;
    }

    public boolean isAvailable() {
        return isAvailable;
    }


}
