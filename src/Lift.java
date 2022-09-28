import java.util.ArrayList;
import java.util.Objects;

public class Lift {
    private int currentFloor;
    private int freeSpace;
    private boolean direction;
    ArrayList<Person> passengers = new ArrayList<>(5);
    Floor floor;

    public Lift () {
        this.currentFloor = 1;
        this.freeSpace = 5;
        this.direction = true;
    }

    public void startWork(Floor floor) {
        this.floor = floor;
        uploading();
        loading(this.floor);
    }

    private void loading(Floor floor) {

        printNumberOfPeople();

        for (int i = 0; i < floor.passengers.size(); i++) {
            Person targetElement = floor.passengers.get(i);
            System.out.println(targetElement.getTargetFloor());

            if (targetElement.getDirection() == direction && freeSpace > 0) {
                passengers.add(targetElement);
                freeSpace--;
                System.out.println("^ Этот сел в лифт: " + targetElement.getDirection());
            }
        }

        for (int i = 0; i < floor.passengers.size(); i++) {
            Person targetElement = floor.passengers.get(i);
            for (Person deleteElement : passengers) {
                if (Objects.equals(targetElement, deleteElement)) {
                    floor.passengers.remove(targetElement);
                    i--;
                    Building.NUMBER_OF_PEOPLE--;
                }
            }
        }

        printNumberOfPeople();
        move();
        uploading();
    }

    private void printNumberOfPeople() {
        System.out.println("\n" + "Количество людей на этаже: " + floor.passengers.size());
        System.out.println("Количество людей в лифет: " + passengers.size() + "\n");
    }

    private void move() {
        if (currentFloor == Building.MAX_FLOOR) {
            direction = !direction;
        }

        if (currentFloor == 1 && !direction) {
            direction = true;
        }

        if(direction) {
            currentFloor++;
            System.out.println(Utilities.separator + "\n" + "Новый этаж: " + currentFloor);
        } else {
            currentFloor--;
            System.out.println(Utilities.separator + "\n" + "Новый этаж: " + currentFloor);
        }
    }

    private void uploading() {
        for (int i = 0; i < passengers.size(); i++) {
            Person targetElement = passengers.get(i);
            if (targetElement.getTargetFloor() == currentFloor) {
                System.out.print("--- Этот целовек вышел из лифта: " + targetElement.getTargetFloor() + "\n");
                passengers.remove(targetElement);
                i--;
                this.freeSpace++;
            }
        }
    }
}
