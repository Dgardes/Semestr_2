package Part_2;

public class Owl
{
    private String type;
    private String name;
    private String gender;
    private double wingSpan;
    private double weight;
    private double speed;

    public Owl(String type, String name, String gender, double wingSpan, double weight, double speed) {
        this.type = type;
        this.name = name;
        this.gender = gender;
        this.wingSpan = wingSpan;
        this.weight = weight;
        this.speed = speed;
    }

    public Owl() {
    }

    @Override
    public String toString() {
        return "Owl{" +
                "type='" + type + '\'' +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", wingSpan=" + wingSpan +
                ", weight=" + weight +
                ", speed=" + speed +
                '}';
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public double getWingSpan() {
        return wingSpan;
    }

    public void setWingSpan(double wingSpan) {
        this.wingSpan = wingSpan;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }
}
