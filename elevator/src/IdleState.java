public class IdleState implements ElevatorState {
    @Override
    public void move(Elevator elevator) {
        if (!elevator.getUpRequests().isEmpty()) {
            elevator.setState(new MovingUpState());
        } else if (!elevator.getDownRequests().isEmpty()) {
            elevator.setState(new MovingDownState());
        }
        // Else stay idle
    }
    @Override
    public void addRequest(Elevator elevator, Request request) {
        if (request.getDestination() > elevator.getCurrentFloor()) {
            elevator.getUpRequests().add(request.getDestination());
        } else if (request.getDestination() < elevator.getCurrentFloor()) {
            elevator.getDownRequests().add(request.getDestination());
        }
    }

    @Override
    public Direction getDirection() { return Direction.IDLE; }
}

