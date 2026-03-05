

public class OrderLineItem {
    int orderid;
    int quantity;
    MenuItem menuItem;
    public OrderLineItem(int orderid, int quantity, MenuItem menuItem) {
        this.orderid = orderid;
        this.quantity = quantity;
        this.menuItem = menuItem;
    }
    public int getOrderid() {
        return orderid;
    }
    public int getQuantity() {
        return quantity;
    }
    public MenuItem getMenuItem() {
        return menuItem;
    }
    
}
