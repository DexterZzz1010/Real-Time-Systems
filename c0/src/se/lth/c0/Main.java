package se.lth.c0;

public class Main {
    public static void printSpeed(Vehicle v) {
        System.out.println("Speed = " + v.getSpeed());
    }

    public static void main(String[] args) {
        Car car1;
        Car car2;
        Car car3;
        car1 = new Car("ABC111", "Volvo V70");
        car2 = new Car("DEF222", "Saab 9-5");
        car3 = new Car("FFF888", "GTR");
        car1.setSpeed(100.0);
        System.out.println("The speed of car1 is " + car1.getSpeed());
        // System.out.println("The number of the car is " + car3.getNumberOfCar());
        printSpeed(car1);
    }
}