import java.util.Random;

public class Person {
    private final boolean direction;
    private int targetFloor;

    public Person(int currentFloor) {
        Random random = new Random();
        this.targetFloor = random.nextInt(1, Building.MAX_FLOOR);

        while (true) {
            if (this.targetFloor != currentFloor) {
                break;
            } else {
                this.targetFloor = random.nextInt(1, Building.MAX_FLOOR);
            }
        }

        this.direction = currentFloor <= targetFloor;
    }

    public int getTargetFloor() {
        return this.targetFloor;
    }

    public boolean getDirection() {
        return this.direction;
    }
}
