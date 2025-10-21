public class ElevatorSystem {
    private final Elevator elevator;
    private final Thread elevatorThread;

    public ElevatorSystem(int maxFloor) {
        this.elevator = new Elevator(maxFloor);
        this.elevatorThread = new Thread(elevator);
        this.elevatorThread.setDaemon(true); 
        this.elevatorThread.start();
    }

    public void requestElevator(int floor) {
        System.out.println("📞 Request received from floor " + floor);
        elevator.addRequest(floor);
    }

    public void shutdown() {
        System.out.println("\n🛑 Shutting down elevator system...");
        elevator.stop();

        try {
            Thread.sleep(500); 
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println("✅ System stopped successfully.");
        System.exit(0); 
    }
}
