import java.util.List;

public class Theater {
    String name;
    String city;
    List<Screen> screens;
    
    public Theater(String name, String city, List<Screen> screens) {
        this.name = name;
        this.city = city;
        this.screens = screens;
    }
}
