package se.lth.c0;// 1.
/*
 * public class Car {
 * private String registrationNumber = "";
 * private String model = "";
 * private double speed = 0.0;
 * 
 * public Car(String number, String carModel) {
 * registrationNumber = number;
 * model = carModel;
 * }
 * 
 * public void setSpeed(double newSpeed) {
 * speed = newSpeed;
 * }
 * 
 * public double getSpeed() {
 * return speed;
 * }
 * 
 * }
 */

// 2.
/*
 * public class Car {
 * private String registrationNumber = "";
 * private String model = "";
 * private double speed = 0.0;
 * private static int numberOfCars = 0;
 * 
 * public Car(String number, String carModel) {
 * registrationNumber = number;
 * model = carModel;
 * numberOfCars++;
 * }
 * 
 * public void setSpeed(double newSpeed) {
 * speed = newSpeed;
 * }
 * 
 * public double getSpeed() {
 * return speed;
 * }
 * 
 * public static int getNumberOfCar() {
 * return numberOfCars;
 * }
 * 
 * }
 */

// 3.

public class Car extends Vehicle {
    private int numberOfPassengers = 0;

    public Car(String registrationNumber, String carModel) {
        super(registrationNumber, carModel);
    }

    public int getNumberOfPassengers() {
        return numberOfPassengers;
    }

    public void setNumberOfPassengers(int passengers) {
        numberOfPassengers = passengers;
    }
}
