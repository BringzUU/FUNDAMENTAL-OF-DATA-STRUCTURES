// File: Car.java


public class Car implements Comparable<Car> {
    private String code;
    private String model;
    private String brand;
    private int price;
    private int year;
    private String type;

    public Car(String code, String model, String brand, int price, int year, String type) {
        this.code = code;
        this.model = model;
        this.brand = brand;
        this.price = price;
        this.year = year;
        this.type = type;
    }

    // Getters for all fields
    public String getCode() {
        return code;
    }

    public String getModel() {
        return model;
    }

    public String getBrand() {
        return brand;
    }

    public int getPrice() {
        return price;
    }

    public int getYear() {
        return year;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return "Car{" + "code=" + code + ", model=" + model + ", brand=" + brand + ", price=" + price + ", year=" + year + ", type=" + type + '}';
    }

    // Implement compareTo for sorting by code
    @Override
    public int compareTo(Car other) {
        return this.code.compareTo(other.code);
    }
}