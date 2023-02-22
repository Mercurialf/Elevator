package modules;

import java.util.ArrayList;
import java.util.Random;

public class Stage
{
    private final int FLOOR_NUMBER;

    private int numberOfPeoplePerFloor;
    private ArrayList<Human> peopleOnFloorList = new ArrayList<>(10);
    private boolean priorityDirection;
    private boolean presenceOfHuman;

    public Stage(int floorNumber) {
        FLOOR_NUMBER = floorNumber;
        Random random = new Random();
        numberOfPeoplePerFloor = random.nextInt(0, 11);

        for (int i = 0; i < numberOfPeoplePerFloor; i++) {
            Human person = new Human(FLOOR_NUMBER);
            peopleOnFloorList.add(person);
        }
        choiceOfPriorityDirection();
    }

    private void setNumberOfPeoplePerFloor(int numberOfPeoplePerFloor) {
        this.numberOfPeoplePerFloor = numberOfPeoplePerFloor;
    }

    public int getNumberOfPeoplePerFloor() {
        return numberOfPeoplePerFloor;
    }

    public int getFLOOR_NUMBER() {
        return FLOOR_NUMBER;
    }

    public ArrayList<Human> getPeopleOnFloorList() {
        return peopleOnFloorList;
    }

    public boolean getPresenceOfHuman() {
        return presenceOfHuman;
    }


    public void addPersonInsteadOneWhoLeft() {
        Human person = new Human(FLOOR_NUMBER);
        peopleOnFloorList.add(person);

        setNumberOfPeoplePerFloor(this.numberOfPeoplePerFloor + 1);
        choiceOfPriorityDirection();
        System.out.println("+++New people created in floor:" + getFLOOR_NUMBER());
    }

    private void choiceOfPriorityDirection() {
        int humanWantUp = 0;
        int humanWantDown = 0;
        presenceOfHuman = peopleOnFloorList.size() > 0;

        for (Human human : peopleOnFloorList) {
            boolean direction = human.getDirection();
            if (direction)
                humanWantUp++;
            else humanWantDown++;
        }

        priorityDirection = humanWantUp >= humanWantDown;
    }

    public void setPeopleOnFloorList(ArrayList<Human> peopleOnFloorList) {
        this.peopleOnFloorList = peopleOnFloorList;
        setNumberOfPeoplePerFloor(this.peopleOnFloorList.size());
        choiceOfPriorityDirection();
    }

    public String toString() {
        return ">|Floor Number:" + FLOOR_NUMBER + "|Number of people per floor:" + numberOfPeoplePerFloor
                + "|Priority Direction:" + priorityDirection + "|Presence of People:" + presenceOfHuman + "|<";
    }
}
