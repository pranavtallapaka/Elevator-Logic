public class Request {
    int floor;
    Direction direction;

    public Request(int floor, Direction direction) {
        this.floor = floor;
        this.direction = direction;
    }

    @Override
    public String toString() {
        return "Request{" + "floor=" + floor + ", direction=" + direction + '}';
    }
}
