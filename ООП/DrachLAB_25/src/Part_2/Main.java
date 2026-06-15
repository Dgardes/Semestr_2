package Part_2;

public class Main {
    public static void main(String[] args) {
        Car myCar = new Car("Tesla Model S", 200.0);
        Motorcycle myBike = new Motorcycle("Yamaha R1", 280.0);

        MyGeneric<Car> carController = new MyGeneric<>(myCar);

        System.out.println(carController.getVehicle().getName());
        double distance = 500.0;
        double carTime = carController.calculateTravelTime(distance);
        System.out.println(carTime);

        MyGeneric<Motorcycle> bikeController = new MyGeneric<>(myBike);

        System.out.println(bikeController.getVehicle().getName());
        double bikeTime = bikeController.calculateTravelTime(distance);
        System.out.println(bikeTime);

        Vehicle faster = carController.getFasterVehicle(myBike);
        System.out.println(faster.getName());
        System.out.println(faster.getSpeed());
    }
}