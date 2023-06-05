package org.example.data.modules;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.example.data.Structure;

import java.util.Random;

@Data
@AllArgsConstructor
public class Human {
    private final boolean DIRECTION;
    private int targetFloor;

    public Human(int currentFloor) {
        Random random = new Random();
        targetFloor = random.nextInt(1, Structure.getMAX_FLOOR());
        while (true) {
            if (targetFloor != currentFloor)
                break;
            else
                targetFloor = random.nextInt(1, Structure.getMAX_FLOOR());
        }
        DIRECTION = targetFloor > currentFloor;
    }

    @Override
    public String toString() {
        return this.hashCode() + " - Direction:" + DIRECTION + " Target Floor:" + getTargetFloor();
    }
}