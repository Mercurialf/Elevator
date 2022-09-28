import java.util.ArrayList;
import java.util.Random;

public class Building {
    public static int MAX_FLOOR;
    public static int NUMBER_OF_PEOPLE;

    public static void getRandomNumber() {
        Random random = new Random();
        MAX_FLOOR = random.nextInt(5, 21);
    }

    public static void printMaxFloor() {
        System.out.println("В здании " + MAX_FLOOR + " этажей.");
    }

    public static void printNumberOfPeople() {
        System.out.println("Количество человек в здании: " + Building.NUMBER_OF_PEOPLE);
    }

    public static void main(String[] args) {
        getRandomNumber();
        printMaxFloor();

        Lift lift = new Lift();
        ArrayList<Floor> floorList = new ArrayList<>(Building.MAX_FLOOR);

        for (int i = 1; i <= Building.MAX_FLOOR; i++) {
            Floor floor = new Floor(i);
            floorList.add(floor);

            Building.NUMBER_OF_PEOPLE += floor.passengers.size();
        }

        printNumberOfPeople();

        while (NUMBER_OF_PEOPLE != 0) {
            for (Floor floor : floorList) {
                lift.startWork(floor);
            }
        }

        printNumberOfPeople();
    }
}
