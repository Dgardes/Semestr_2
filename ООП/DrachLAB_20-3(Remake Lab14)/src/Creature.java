import java.util.Scanner;

public abstract class Creature
{
    protected String name;
    protected String description;

    protected int maxHitPoints;
    protected int hitPoints;
    protected int damage;

    public abstract void attack(Creature target);
    public abstract void makeSound();
    public abstract void eat(Scanner scanner);

    public void sleep()
    {
        System.out.println("відновлюємо сили. . .");
        this.hitPoints = this.maxHitPoints;
        System.out.println("сили відновлено! ХП: " + this.hitPoints + "/" + this.maxHitPoints);
    }

    public void takeDamage(int damage)
    {
        this.hitPoints -= damage;
        if (this.hitPoints <= 0)
        {
            System.out.println("Це створіння померло");
        }
        else
        {
            System.out.println("Отримано урон: -" + damage + "хп: " + this.hitPoints);
        }
    }

    public void takeHealth(int health)
    {
        this.hitPoints += health;
        if (this.hitPoints > maxHitPoints)
        {
            this.hitPoints = this.maxHitPoints;
        }
        System.out.println("Додано ХП: +" + health + "ХП: " + this.hitPoints + "/" + this.maxHitPoints);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getMaxHitPoints() {
        return maxHitPoints;
    }

    public void setMaxHitPoints(int maxHitPoints) {
        this.maxHitPoints = maxHitPoints;
    }

    public int getHitPoints() {
        return hitPoints;
    }

    public void setHitPoints(int hitPoints) {
        this.hitPoints = hitPoints;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    @Override
    public String toString() {
        return "Creature{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", maxHitPoints=" + maxHitPoints +
                ", hitPoints=" + hitPoints +
                ", damage=" + damage +
                '}';
    }
}
