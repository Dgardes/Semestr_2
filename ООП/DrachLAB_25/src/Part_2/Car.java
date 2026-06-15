package Part_2;

public class Car implements Vehicle {
    private String name;
    private double speed;

    public Car(String name, double speed) {
        this.name = name;
        this.speed = speed;
    }

    @Override
    public String getName() { return name; }

    @Override
    public double getSpeed() { return speed; }
}
