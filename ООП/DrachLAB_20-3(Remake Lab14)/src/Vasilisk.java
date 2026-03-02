import java.util.Scanner;

public class Vasilisk extends Creature
{
    public Vasilisk()
    {
        this.name = "Ноу Нейм";
        this.description = "Без опису";
        this.maxHitPoints = 100;
        this.hitPoints = this.maxHitPoints;
        this.damage = 25;
    }

    public Vasilisk(String name)
    {
        this.name = name;
        this.description = "Без опису";
        this.maxHitPoints = 100;
        this.hitPoints = this.maxHitPoints;
        this.damage = 25;
    }

    public Vasilisk(String name, String description, int hp, int dmg)
    {
        this.name = name;
        this.description = description;
        this.maxHitPoints = hp;
        this.hitPoints = hp;
        this.damage = dmg;
    }

    @Override
    public void attack(Creature target) {
        System.out.println("Василіск " + this.name + " атакує");
        System.out.println("Василіск наносить урон цілі " + target.getName() + " у розмірі " + this.damage + " одиниць");
        target.takeDamage(this.damage);
    }

    @Override
    public void makeSound() {
        System.out.println("ШШШШ, Кукуріку");
    }

    @Override
    public void sleep() {
        super.sleep();
    }

    @Override
    public void eat(Scanner scanner) {
        System.out.println("Чи буде цей Василіск сьогодні обідати?");
        System.out.println("1 – так (+ 10 ХП), 2 – ні (- 10 ХП)");

        while(!scanner.hasNextInt())
        {
            System.out.println("Ви ввели невірне число, спробуйте ще раз");
            scanner.next();
        }
        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice)
        {
            case(1):
            {takeHealth(10); break;}
            case(2):
            {takeDamage(10); break;}
            default:
            { System.out.println("Ви ввели невірний номер дії. Василіск роздратований."); takeDamage(10);}
        }
    }

}
