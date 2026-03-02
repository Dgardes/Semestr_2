import java.util.Scanner;

public class Main {
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        GameManager manager = new GameManager();

        System.out.println("створюємо створінь");
        Creature creature_1 = manager.createCreature(scanner);
        System.out.println(creature_1.toString());

        Creature creature_2 = manager.createCreature(scanner);
        System.out.println(creature_2.toString());

        Creature creature_3 = manager.createCreature(scanner);
        System.out.println(creature_3.toString());

        System.out.println("створенний програмно василіск:");
        Vasilisk vas = new Vasilisk("Васьок");
        System.out.println("ім'я: " + vas.getName());
        System.out.println("опис: " + vas.getDescription());
        System.out.println("шкода: " + vas.getDamage());
        System.out.println("максимальне ХП: " + vas.getMaxHitPoints());
        System.out.println("поточне ХП: " + vas.getHitPoints());

        System.out.println("Змагання: ");
        manager.competition(creature_1, creature_2, creature_3);
    }
}