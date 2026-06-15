package Part_3;

public class Weapon implements Item {
    private String name;
    private int damage;

    public Weapon(String name, int damage) {
        this.name = name;
        this.damage = damage;
    }

    @Override
    public String getName() { return name; }

    @Override
    public int getBaseValue() { return damage; }
}
