package modules;

import java.util.ArrayList;
import java.util.Random;
import utilities.Utility;

public class Structure
{
    private static final int MAX_FLOOR;

    static {
        Random random = new Random();
        MAX_FLOOR = random.nextInt(5, 21);
    }

    private final ArrayList<Stage> stageArrayList;
    private final Elevator elevator;
    private int totalNumberOfPeople;

    public Structure() {
        this.stageArrayList = new ArrayList<>(Structure.getMaxFloor());
        this.elevator = new Elevator(1);

        for (int i = 1; i <= Structure.getMaxFloor(); i++) {
            Stage stage = new Stage(i);
            this.stageArrayList.add(stage);
        }
    }

    public static int getMaxFloor() {
        return MAX_FLOOR;
    }

    private void printFullInfoAboutStage() {
        countingTotalNumberOfPeople();
        System.out.println("Maximum floor:" + Structure.getMaxFloor() +
                " Total Number Of People:" + totalNumberOfPeople);
        for (Stage stage : this.stageArrayList) {
            System.out.println(stage.toString());
        }
    }

    private void countingTotalNumberOfPeople() {
        int counter = 0;

        for (Stage stage : stageArrayList) {
            counter += stage.getNumberOfPeoplePerFloor();
        }
        counter += elevator.getNumberOfPeopleInElevator();

        totalNumberOfPeople = counter;
    }

    /**
     * Comment out the "checkingIfSomeoneHasLeft" method
     * so as not to create new people in place of the departed.
     */
    public void Start(int numberOfLiftIterations) {
        printFullInfoAboutStage();

        for (int x = 0; x < numberOfLiftIterations; x++) {
            Utility.printSeparator();
            System.out.println(elevator);

            countingTotalNumberOfPeople();
            elevator.exitPeopleFromElevator();

            int numberOfPeopleBeforeCounting = totalNumberOfPeople;
            countingTotalNumberOfPeople();
            checkingIfSomeoneHasLeft(numberOfPeopleBeforeCounting);

            stageArrayList.get(elevator.getCurrentFloor() - 1).setPeopleOnFloorList(elevator.loadingPeopleIntoElevator
                    (stageArrayList.get(elevator.getCurrentFloor() - 1).getPeopleOnFloorList()));

            System.out.println(stageArrayList.get(elevator.getCurrentFloor() - 1).toString());

            elevator.Move(stageArrayList);
            System.out.println(elevator);
            Utility.printSeparator();
        }

        countingTotalNumberOfPeople();
        printFullInfoAboutStage();
    }

    private void checkingIfSomeoneHasLeft(int numberOfPeopleBeforeCounting) {
        System.out.println(totalNumberOfPeople);
        if (numberOfPeopleBeforeCounting != totalNumberOfPeople) {
            addingNewPersonToRandomFloor(numberOfPeopleBeforeCounting, totalNumberOfPeople);
        }
    }

    private void addingNewPersonToRandomFloor(int pastAmount, int currentAmount) {
        for (int i = 0; i < (pastAmount - currentAmount); i++) {
            Random random = new Random();
            int randomNumber = random.nextInt(0, Structure.getMaxFloor());

            while (stageArrayList.get(randomNumber).getNumberOfPeoplePerFloor() >= 10) {
                randomNumber = random.nextInt(0, Structure.getMaxFloor());
            }

            stageArrayList.get(randomNumber).addPersonInsteadOneWhoLeft();
        }
    }
}
