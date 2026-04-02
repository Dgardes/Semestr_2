package Part_3;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

public class Main
{
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        String fileName;
        Auditory316Inventory au = null;
        System.out.print("Введіть назву файлу: ");

        while (true)
        {
            fileName = scanner.next() + ".dat";
            if (!Files.exists(Path.of(fileName)))
            {
                try
                {
                    Files.createFile(Path.of(fileName));
                    au = new Auditory316Inventory(fileName);
                    au.serializeItems(new ArrayList<InventoryItem>());
                    break;
                }
                catch (Exception e)
                {
                    System.out.print("не вірний формат вводу, спробуйте ще раз: ");
                    scanner.nextLine();
                }
            }
        }
        au = new Auditory316Inventory(fileName);

        while (true)
        {
            printMenu();

            int answer = au.validateInt(0, 4);
            switch (answer)
            {
                case 1:
                    System.out.println("інвентар на обліку: ");
                    au.showItems();
                    break;
                case 2:
                    au.addItem();
                    break;
                case 3:
                    au.editItem();
                    break;
                case 4:
                    au.deleteItem();
                    break;
                case 0:
                    System.out.println("програму завершено.");
                    return;
            }
        }

    }

    public static void printMenu()
    {
        System.out.println("меню: ");
        System.out.println("----------------------------------------");
        System.out.println("(1): показати інвентар на обліку");
        System.out.println("(2): додати новий інвентар до обліку");
        System.out.println("(3): Відредагувати дані про інвентар");
        System.out.println("(4): Видалити інвентар");
        System.out.println("(0): завершити роботу");
        System.out.println("----------------------------------------");
        System.out.print("Введіть номер дії: ");
    }
}