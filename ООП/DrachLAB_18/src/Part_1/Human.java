package Part_1;

public class Human {
    private String name;
    public Human(String name) {
        this.name = name;
    }
    @Override
    public String toString() {
        return "Человек с именем " + name;
    }
}
