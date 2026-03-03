import java.util.ArrayList;
import java.util.List;

public class Screen {
    int ScreenNumber;
    List<Seat> seats;
    
    public Screen(int screenNumber) {
        this.ScreenNumber = screenNumber;
        this.seats = new ArrayList<>();
    }
    
    public void addSeat(Seat seat) {
        this.seats.add(seat);
    }
    
    public List<Seat> getSeats() {
        return this.seats;
    }

}
