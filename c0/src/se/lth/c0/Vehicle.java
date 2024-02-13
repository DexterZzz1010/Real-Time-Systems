package se.lth.c0;

public class Vehicle {
    private String registrationNumber = "";
    private String model = "";
    private double speed = 0.0;
    private static int numberOfVehicles = 0;

    public Vehicle(String number, String vehicalModel) {
        registrationNumber = number;
        model = vehicalModel;
        numberOfVehicles++;
    }

    public void setSpeed(double newSpeed) {
        speed = newSpeed;
    }

    public double getSpeed() {
        return speed;
    }

    public static int getNumberOfVehicales() {
        return numberOfVehicles;

    }
}
