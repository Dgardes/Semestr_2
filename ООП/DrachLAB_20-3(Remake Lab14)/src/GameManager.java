import java.util.Scanner;

public class GameManager {

    public Creature createCreature(Scanner scanner) {

        String name = "";
        String description = "";
        int hp = 1;
        int dmg = 1;

        System.out.println("створення створіння");
        System.out.println("1 – Василіск ");
        System.out.println("2 – Робот ");
        System.out.println("оберіть варіант");

        int choice = getInt(scanner, 1, 2);

        System.out.println("Оберіть як заповнити дані");
        System.out.println("1 – авто");
        System.out.println("2 – тільки ім'я");
        System.out.println("3 – всі поля");

        int choiceMode = getInt(scanner, 1, 3);

        if (choiceMode == 2)
        {
            System.out.println("Введіть ім'я");
            name = scanner.nextLine();
        }

        if (choiceMode == 3)
        {
            System.out.println("Введіть ім'я ");
            name = scanner.nextLine();

            System.out.println("Введіть опис ");
            description = scanner.nextLine();

            System.out.println("Введіть максимальну кількість ХП");
            hp = getInt(scanner, 1, 9999);

            System.out.println("Введіть урон");
            dmg = getInt(scanner, 1, 9999);
        }

        if (choice == 1)
        {
            switch (choiceMode)
            {
                case 1:
                    return new Vasilisk();
                case 2:
                    return new Vasilisk(name);
                case 3:
                    return new Vasilisk(name, description, hp, dmg);
                default:
                    return new Vasilisk();
            }
        }
        else
        {
            switch (choiceMode)
            {
                case 1:
                    return new Robot();
                case 2:
                    return new Robot(name);
                case 3:
                    return new Robot(name, description, hp, dmg);
                default:
                    return new Robot();
            }
        }
    }

    private int getInt(Scanner scanner, int minNum, int maxNum)
    {
        while(true)
        {
            try
            {
                int choice = scanner.nextInt();
                if(choice >= minNum && choice <= maxNum)
                {
                    return choice;
                }
                else
                {
                    System.out.println("Ви ввели число за межами діапазону, спробуйте ще раз");
                }
            }
            catch (Exception e)
            {
                System.out.println("Ви не ввели ціле число, спробуйте ще раз");
                scanner.next();
            }
        }
    }

    public void competition(Creature cr_1, Creature cr_2, Creature cr_3)
    {
        Creature winner = battle(cr_1, cr_2);
        if(winner == cr_1)
        {
            winner = battle(cr_1, cr_3);
        }
        else
        {
            winner = battle(cr_2, cr_3);
        }
        System.out.println("Переможцем серед трьох створінь є " + winner.getName());
    }

    private Creature battle(Creature cr_1, Creature cr_2)
    {
        System.out.println("створіння під ім'ям " + cr_1.getName() + " проти створіння під ім'ям " + cr_2.getName());
        while(true)
        {
            cr_1.attack(cr_2);
            if(cr_2.getHitPoints() <= 0)
            {
                System.out.println("переміг " + cr_2.getName());
                cr_1.sleep();
                cr_2.sleep();
                return cr_2;
            }

            cr_2.attack(cr_1);
            if(cr_1.getHitPoints() <= 0)
            {
                System.out.println("переміг " + cr_1.getName());
                cr_1.sleep();
                cr_2.sleep();
                return cr_1;
            }
        }
    }
}