public class Seat {
    private final int row;
    private final int number;
    private final SeatType seatType;
    private boolean isBooked;

    public Seat(int row, int number, SeatType seatType) {
        this.row = row;
        this.number = number;
        this.seatType = seatType;
        this.isBooked = false;
    }

    public int getRow() { return row; }
    public int getNumber() { return number; }
    public SeatType getSeatType() { return seatType; }

    public synchronized boolean book() {
        if (isBooked) return false;
        isBooked = true;
        return true;
    }

    public synchronized void unbook() {
        isBooked = false;
    }

    public synchronized boolean isBooked() {
        return isBooked;
    }
}