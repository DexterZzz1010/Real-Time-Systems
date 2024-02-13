package se.lth.c0;

public class Truck extends Vehicle {
    private int currentLoad = 0;

    public Truck(String registrationNumber, String truckModel) {
        super(registrationNumber, truckModel);
    }

    public int getCurrentLoad() {
        return currentLoad;
    }

    public void setCurrentLoad(int load) {
        currentLoad = load;
    }
}
