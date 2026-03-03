public class MovingUpState implements ElevatorState {
    @Override
    public void move(Elevator elevator) {
        if(elevator.getUpRequests().isEmpty()) {
            elevator.setState(new IdleState());
        } else {
            // Move up logic
            elevator.setCurrentFloor(elevator.getUpRequests().pollFirst());
        }
        System.out.println("Elevator is moving up.");
    }

    @Override
    public void addRequest(Elevator elevator, Request request) {
        if(request.getDestination() > elevator.getCurrentFloor()) {
            elevator.getUpRequests().add(request.getDestination());
        } else if(request.getDestination() < elevator.getCurrentFloor()) {
            elevator.getDownRequests().add(request.getDestination());
        }
        System.out.println("Adding request while moving up: " + request);
    }

    @Override
    public Direction getDirection() {
        return Direction.UP;
    }
    
}
