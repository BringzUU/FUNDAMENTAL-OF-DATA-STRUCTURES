// File: MyArray.java


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MyArray {
    private ArrayList<Car> data;

    public MyArray() {
        this.data = new ArrayList<>();
    }
    
    // Method to get all cars for display
    public List<Car> getCars() {
        return data;
    }

    // Add a car to the beginning of the list
    public void addFirst(Car car) {
        data.add(0, car);
    }

    // Add a car to the end of the list
    public void addLast(Car car) {
        data.add(car);
    }

    // Delete the first car from the list
    public void deleteFirst() {
        if (!data.isEmpty()) {
            data.remove(0);
        }
    }

    // Delete the last car from the list
    public void deleteLast() {
        if (!data.isEmpty()) {
            data.remove(data.size() - 1);
        }
    }

    // Sort the list of cars (uses the compareTo method in Car.java)
    public void sort() {
        Collections.sort(data);
    }
}