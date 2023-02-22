package modules;

import java.util.Random;

public class Human
{
    private final boolean DIRECTION;
    private int targetFloor;

    public Human(int currentFloor) {
        Random random = new Random();
        targetFloor = random.nextInt(1, Structure.getMaxFloor());
        while (true)
        {
            if (targetFloor != currentFloor)
                break;
            else
                targetFloor = random.nextInt(1, Structure.getMaxFloor());
        }
        DIRECTION = targetFloor > currentFloor;
    }

    public boolean getDirection() { return DIRECTION; }
    public int getTargetFloor() { return targetFloor; }

    public String toString() {
        return this.hashCode() + " - Direction:" + getDirection() + " Target Floor:" + getTargetFloor();
    }
}
