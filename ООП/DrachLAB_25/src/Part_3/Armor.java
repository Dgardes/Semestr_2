package Part_3;

public class Armor implements Item {
    private String name;
    private int defense;

    public Armor(String name, int defense) {
        this.name = name;
        this.defense = defense;
    }

    @Override
    public String getName() { return name; }

    @Override
    public int getBaseValue() { return defense; }
}
