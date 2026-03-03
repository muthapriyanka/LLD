
public class ElevatorDisplay implements ElevatorObserver {
    private Elevator elevator;

    public ElevatorDisplay(Elevator elevator) {
        this.elevator = elevator;
    }

    @Override
    public void update(Elevator elevator) {
        System.out.println("Elevator is at floor: " + elevator.getCurrentFloor() + 
                           " and state: " + elevator.getState());
    }
    
}
