package Part_1;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main
{
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);

        String fileName = "MyOwl.txt";
        String name, weight, feathersColor, wingspan, isNightHunter, speed;

        System.out.print("Введіть ім'я сови: "); name = scanner.nextLine();
        System.out.print("Введіть вагу сови: "); weight = scanner.nextLine();
        System.out.print("Введіть колір пір'я сови: "); feathersColor = scanner.nextLine();
        System.out.print("Введіть розмах крил сови: "); wingspan = scanner.nextLine();
        System.out.print("Чи є ваша сова нічним хижаком?: "); isNightHunter = scanner.nextLine();
        System.out.print("Введіть швидкість сови: "); speed = scanner.nextLine();

        String toSave =
        (
            "Ім'я – " + name + "\n" +
            "Вага – " + weight + "\n" +
            "Колір пір'я – " + feathersColor + "\n" +
            "Розмах крил – " + wingspan + "\n" +
            "Є нічним хижаком – " + isNightHunter + "\n" +
            "Швидкість – " + speed
        );

        FileWriter fw = null;

        try
        {
            fw = new FileWriter(fileName);
            fw.write(toSave);
            fw.close();
        }
        catch (Exception e)
        {
            switch (e.getClass().getSimpleName())
            {
                case "FileNotFoundException": break; //викключення 1
                case "AccessDeniedException": break; //викключення 2
                case "IOException": break; //викключення 3
            }
        }
    }
}