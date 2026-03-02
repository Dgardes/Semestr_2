import java.util.Scanner;

public class Robot extends Creature
{
    public Robot()
    {
        this.name = "кіборг-вбивця";
        this.description = "без жалості";
        this.maxHitPoints = 150;
        this.hitPoints = this.maxHitPoints;
        this.damage = 20;
    }

    public Robot(String name)
    {
        this.name = name;
        this.description = "без жалості";
        this.maxHitPoints = 150;
        this.hitPoints = this.maxHitPoints;
        this.damage = 20;
    }

    public Robot(String name, String description, int hp, int dmg)
    {
        this.name = name;
        this.description = description;
        this.maxHitPoints = hp;
        this.hitPoints = hp;
        this.damage = dmg;
    }

    @Override
    public void attack(Creature target)
    {
        System.out.println("Робот " + this.name + " робить пів-пав");
        System.out.println("Робот наносить урон цілі " + target.getName() + " у розмірі " + this.damage + " одиниць");
        target.takeDamage(this.damage);
    }

    @Override
    public void makeSound()
    {
        System.out.println("скрежет заліза");
    }

    @Override
    public void sleep()
    {
        System.out.println("Перехід у режим очікування. . .");
        super.sleep();
    }

    @Override
    public void eat(Scanner scanner) {
        System.out.println("Чи побачить робот додаткові % заряду?");
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
            { System.out.println("Ви ввели невірний номер дії. Робот роздратований."); takeDamage(10);}
        }
    }
}