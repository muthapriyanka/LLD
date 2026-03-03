public class MovingDownState implements ElevatorState {
    @Override
    public void move(Elevator elevator) {
        if (!elevator.getDownRequests().isEmpty()) {
            int nextFloor = elevator.getDownRequests().pollLast();
            elevator.setCurrentFloor(nextFloor);
        } else {
            elevator.setState(new IdleState());
        }
    }

    @Override
    public void addRequest(Elevator elevator, Request request) {
        if (request.getDestination() < elevator.getCurrentFloor()) {
            elevator.getDownRequests().add(request.getDestination());
        } else if (request.getDestination() > elevator.getCurrentFloor()) {
            elevator.getUpRequests().add(request.getDestination());
        }
        // If request is for current floor, doors would open (handled implicitly by moving to that floor)
    }

    @Override
    public Direction getDirection() { return Direction.DOWN; }

}
