import java.util.ArrayList;
import java.util.Random;

public class Floor {
    ArrayList<Person> passengers = new ArrayList<>(10);

    public Floor(int floorNumber) {
        Random random = new Random();
        int numberOfPeople = random.nextInt(1, 11);

        for (int i = 0; i < numberOfPeople; i++) {
            Person person = new Person(floorNumber);
            passengers.add(person);
        }
    }

    public ArrayList<Person> getPassengers() {
        return passengers;
    }
}
