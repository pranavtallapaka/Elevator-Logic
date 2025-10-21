public class ElevatorSimulation {
    public static void main(String[] args) throws InterruptedException {
        ElevatorSystem system = new ElevatorSystem(10);

        system.requestElevator(3);
        Thread.sleep(1000);
        system.requestElevator(7);
        Thread.sleep(1000);
        system.requestElevator(2);
        Thread.sleep(1000);
        system.requestElevator(9);

        Thread.sleep(15000);
        system.shutdown(); 
    }
}
