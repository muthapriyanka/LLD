public class Request {
    int source;
    int destination;
    RequestSource requestSource;
    Direction direction;

    public Request(int targetFloor, Direction direction, RequestSource source) {
        this.destination = targetFloor;
        this.direction = direction;
        this.requestSource = source;
    }

    public int getSource() {
        return source;
    }

    public void setSource(int source) {
        this.source = source;
    }

    public int getDestination() {
        return destination;
    }

    public void setDestination(int destination) {
        this.destination = destination;
    }

    public Direction getDirection() {
        return direction;
    }
}
