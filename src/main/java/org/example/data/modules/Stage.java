package org.example.data.modules;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.Random;

@Data
@AllArgsConstructor
public class Stage {
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
            boolean direction = human.isDIRECTION();
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
}