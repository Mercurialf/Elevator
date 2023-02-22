package modules;

import java.util.ArrayList;

public class Elevator
{
    private int currentFloor;
    private int numberOfPeopleInElevator;
    private final ArrayList<Human> peopleInElevatorList = new ArrayList<>(5);
    private boolean priorityDirection;

    public Elevator( int currentFloor) {
        if (currentFloor > Structure.getMaxFloor() || currentFloor < 1) {
            this.currentFloor = 1;
        } else {
            this.currentFloor = currentFloor;
        }

        numberOfPeopleInElevator = 0;
        priorityDirection = Structure.getMaxFloor() / 2 > this.currentFloor;
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public int getNumberOfPeopleInElevator() {
        return numberOfPeopleInElevator;
    }

    public String toString() {
        return "|Current Elevator floor:" + currentFloor
                + "|Number of People in the Elevator:" + numberOfPeopleInElevator
                + "|Priority Direction:" + priorityDirection + "|";
    }

    public void Move(ArrayList<Stage> stageArrayList) {
        if (numberOfPeopleInElevator == 0) {
            selectionNewMovementPriority(stageArrayList);
        }

        if (priorityDirection && currentFloor != Structure.getMaxFloor()) {
            currentFloor++;
        } else if (!priorityDirection && currentFloor != 1) {
            currentFloor--;
        }

        if (currentFloor == Structure.getMaxFloor() & priorityDirection) {
            priorityDirection = false;
        }
        if (currentFloor == 1 & !priorityDirection) {
            priorityDirection = true;
        }
    }

    public ArrayList<Human> loadingPeopleIntoElevator(ArrayList<Human> peopleOnFloorList) {
        if (numberOfPeopleInElevator == 5) {
            return peopleOnFloorList;
        }

        for (int i = 0; i < peopleOnFloorList.size(); i++) {
            Human human = peopleOnFloorList.get(i);
            System.out.println(human.toString());

            if (human.getDirection() == priorityDirection) {
                peopleInElevatorList.add(human);
                numberOfPeopleInElevator++;
                System.out.println("^ This man got into the elevator");

                peopleOnFloorList.remove(human);
                i--;
            }

            if (numberOfPeopleInElevator == 5) {
                break;
            }
        }

        return peopleOnFloorList;
    }

    public void exitPeopleFromElevator() {
        for (int i = 0; i < peopleInElevatorList.size(); i++) {
            Human human = peopleInElevatorList.get(i);

            if (human.getTargetFloor() == currentFloor) {
                System.out.println("--- This man is out: " + human);
                peopleInElevatorList.remove(human);
                i--;
                this.numberOfPeopleInElevator--;
            }
        }
    }

    public void selectionNewMovementPriority(ArrayList<Stage> stageArrayList) {
        boolean presenceOfPeople = false;

        if (priorityDirection) {
            for (int i = currentFloor - 1; i < Structure.getMaxFloor(); i++) {
                presenceOfPeople = stageArrayList.get(i).getPresenceOfHuman();
                if (presenceOfPeople)
                    break;
            }
        }
        if (!priorityDirection) {
            for (int i = currentFloor - 1; i > 0; i--) {
                presenceOfPeople = stageArrayList.get(i).getPresenceOfHuman();
                if (!presenceOfPeople)
                    break;
            }
        }

        this.priorityDirection = presenceOfPeople;
    }
}