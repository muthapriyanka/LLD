import java.util.ArrayList;
import java.util.List;

public class Show {
    private final int showId;
    private final int time; // or LocalDateTime in real system
    private final Screen screen;
    private final Movie movie;
    private final List<Seat> seats; // seats for this show (independent state)

    public Show(int showId, int time, Movie movie, Screen screen) {
        this.showId = showId;
        this.time = time;
        this.movie = movie;
        this.screen = screen;
        this.seats = new ArrayList<>();

        // copy seats from screen into per-show seats (so booking state is per-show)
        for (Seat s : screen.getSeats()) {
            this.seats.add(new Seat(s.getRow(), s.getNumber(), s.getSeatType()));
        }
    }

    public Seat getSeat(int seatNumber) {
        return seats.stream()
                .filter(s -> s.getNumber() == seatNumber)
                .findFirst()
                .orElse(null);
    }

    public List<Seat> getSeats() {
        return seats;
    }

    // getters for movie, time, id etc.
}