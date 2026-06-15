package Part_2;

public class MyGeneric<T extends Vehicle> {
    private T vehicle;

    public MyGeneric(T vehicle) {
        this.vehicle = vehicle;
    }

    public double calculateTravelTime(double distanceInKm) {
        if (vehicle.getSpeed() <= 0) {
            return 0;
        }
        return distanceInKm / vehicle.getSpeed();
    }

    public Vehicle getFasterVehicle(Vehicle otherVehicle) {
        if (this.vehicle.getSpeed() >= otherVehicle.getSpeed()) {
            return this.vehicle;
        } else {
            return otherVehicle;
        }
    }

    public T getVehicle() {
        return this.vehicle;
    }
}
