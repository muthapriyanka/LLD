

import java.util.ArrayList;
import java.util.List;

public class Menu {
    int menuid;
    String name;
    List<MenuItem> menuItems;
    public Menu(int menuid, String name, List<MenuItem> menuItems) {
        this.menuid = menuid;
        this.name = name;
        this.menuItems = menuItems;
    }
    public Menu() {
        this.menuid = 0;
        this.name = "";
        this.menuItems = new ArrayList<>();
    }
    public int getMenuid() {
        return menuid;  
    }
    public String getName() {
        return name;
    }   
    public List<MenuItem> getMenuItems() {
        return menuItems;
    }
    public void addItem(MenuItem item) {
        this.menuItems.add(item);
    }
    
}
