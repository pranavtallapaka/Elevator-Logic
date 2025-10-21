import java.util.*;

public class Elevator implements Runnable {
    private int currentFloor;
    private Direction direction;
    private final int maxFloor;
    private boolean running = true;

    private final List<Integer> upQueue = new ArrayList<>();
    private final List<Integer> downQueue = new ArrayList<>();

    public Elevator(int maxFloor) {
        this.currentFloor = 0;
        this.direction = Direction.IDLE;
        this.maxFloor = maxFloor;
    }

    public synchronized void addRequest(int floor) {
        if (!running) return;

        if (floor < 0 || floor > maxFloor) {
            System.out.println("Invalid floor: " + floor);
            return;
        }
        if (floor > currentFloor) upQueue.add(floor);
        else if (floor < currentFloor) downQueue.add(floor);
        else System.out.println("Elevator already on floor " + floor);

        Collections.sort(upQueue);
        Collections.sort(downQueue, Collections.reverseOrder());
        notify();
    }

    private void moveToFloor(int floor) throws InterruptedException {
        if (!running) return;

        direction = floor > currentFloor ? Direction.UP : Direction.DOWN;
        System.out.println("\n▶ Moving " + direction + " from " + currentFloor + " to " + floor);

        while (running && currentFloor != floor) {
            Thread.sleep(1000);
            currentFloor += direction == Direction.UP ? 1 : -1;
            System.out.println("... passing floor " + currentFloor);
        }

        System.out.println("✅ Arrived at floor " + floor);
        System.out.println("Doors opening... 🚪");
        Thread.sleep(1000);
        System.out.println("Doors closing... 🚪");
    }

    @Override
    public void run() {
        try {
            while (running) {
                synchronized (this) {
                    while (upQueue.isEmpty() && downQueue.isEmpty() && running) {
                        direction = Direction.IDLE;
                        wait();
                    }
                }

                if (!upQueue.isEmpty()) {
                    for (int floor : new ArrayList<>(upQueue)) {
                        if (!running) break;
                        moveToFloor(floor);
                        upQueue.remove(Integer.valueOf(floor));
                    }
                }

                if (!downQueue.isEmpty()) {
                    for (int floor : new ArrayList<>(downQueue)) {
                        if (!running) break;
                        moveToFloor(floor);
                        downQueue.remove(Integer.valueOf(floor));
                    }
                }

                direction = Direction.IDLE;
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        System.out.println("🚪 Elevator thread stopped.");
    }

    public synchronized void stop() {
        running = false;
        notifyAll();
    }
}
