package Part_2;

    public class Motorcycle implements Vehicle {
    private String name;
    private double speed;

    public Motorcycle(String name, double speed) {
        this.name = name;
        this.speed = speed;
    }

    @Override
    public String getName() { return name; }

    @Override
    public double getSpeed() { return speed; }
}