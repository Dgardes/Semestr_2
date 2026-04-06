package Part_3;

public class Bicycle
{
    private int ID;
    private String brand;
    private String model;
    private int wheelSize;
    private double weight;
    private boolean isElectric;
    private int price;

    public Bicycle(String brand, String model, int wheelSize, double weight, boolean isElectric, int price) {

        this.ID = (int) (Math.random() * 999999);
        this.brand = brand;
        this.model = model;
        this.wheelSize = wheelSize;
        this.weight = weight;
        this.isElectric = isElectric;
        this.price = price;
    }

    public Bicycle()
    {
        this.ID = (int) (Math.random() * 999999);
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getWheelSize() {
        return wheelSize;
    }

    public void setWheelSize(int wheelSize) {
        this.wheelSize = wheelSize;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public boolean isElectric() {
        return isElectric;
    }

    public void setElectric(boolean electric) {
        isElectric = electric;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getID()
    {
        return this.ID;
    }

    @Override
    public String toString() {
        return "Bicycle{" +
                "ID=" + ID +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", wheelSize=" + wheelSize +
                ", weight=" + weight +
                ", isElectric=" + isElectric +
                ", price=" + price +
                '}';
    }
}
