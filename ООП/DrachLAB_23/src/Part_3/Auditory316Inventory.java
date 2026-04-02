package Part_3;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Auditory316Inventory implements Serializable
{
    private String fileName;

    public Auditory316Inventory(String fileName) {
        this.fileName = fileName;
    }

    public void serializeItems(ArrayList<InventoryItem> items)
    {
        try
        {
            ObjectOutputStream oos = new ObjectOutputStream( new FileOutputStream(fileName));
            oos.writeObject(items);
        }
        catch (IOException e) { throw new RuntimeException(e); }
    }

    public ArrayList<InventoryItem> deserializeItems()
    {
        ArrayList<InventoryItem> items = null;
        try
        {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName));
            items = (ArrayList<InventoryItem>) ois.readObject();
        }
        catch (IOException e) { throw new RuntimeException(e); }
        catch (ClassNotFoundException e) { throw new RuntimeException(e); }

        return items;
    }

    public int validateInt(int min, int max)
    {
        Scanner scanner = new Scanner(System.in);
        System.out.print("введіть число у діапазоні від " + min + " до " + max + ": ");

        while(true)
        {
            while(!scanner.hasNextInt())
            {
                System.out.print("ви ввели число некоректно, спробуйте ще раз: ");
                scanner.nextLine();
            }
            int i = scanner.nextInt();

            if(i >= min && i <= max)
            {
                return i;
            }
            else
            {
                System.out.print("Ви ввели число у невірному діапазоні. Спробуйте ще раз: ");
            }
        }
    }

    public void showItems()
    {
        ArrayList<InventoryItem> items = deserializeItems();
        for(int i = 0; i < items.size(); i++)
        {
            System.out.println
            (
                "№" + i + " inventoryNum: " + items.get(i).getInventoryNum() +
                ", name: " + items.get(i).getName() +
                ", count: " + items.get(i).getCount() +
                ", description: " + items.get(i).getDescription()
            );
        }
    }

    //
    //
    //

    public void addItem()
    {
        ArrayList<InventoryItem> items = deserializeItems();
        Scanner scanner = new Scanner(System.in);
        String inventoryNum, name, description;
        int count;

        System.out.print("Введіть інвентаризаційний номер: "); inventoryNum = scanner.nextLine();
        System.out.print("Введіть назву об'єкту: "); name = scanner.nextLine();
        System.out.print("Введіть опис об'єкту: "); description = scanner.nextLine();
        System.out.print("Введіть кількість об'єкту: ");
        while(!scanner.hasNextInt())
        {
            System.out.print("Ви ввели не коректне число, спробуте ще раз: ");
            scanner.nextLine();
        }
        count = scanner.nextInt();
        scanner.nextLine();

        InventoryItem item = new InventoryItem(inventoryNum, name, count, description);
        items.add(item);
        System.out.println("елемент додано");
        serializeItems(items);
    }

    public void deleteItem()
    {
        ArrayList<InventoryItem> items = deserializeItems();

        if(items.isEmpty())
        {
            System.out.println("відсутній інвентар на обліку. Видаляти нічого.");
            return;
        }
        System.out.println("інвентар на обліку: ");
        showItems();
        int max = items.size() - 1;
        System.out.println("введіть номер елементу, який потрібно видалити");
        items.remove(validateInt(0, max));

        System.out.println("елемент видалено");
        serializeItems(items);
    }

    public void editItem()
    {
        Scanner scanner = new Scanner(System.in);

        ArrayList<InventoryItem> items = deserializeItems();
        int itemIndex, propertyIndex;
        int max = items.size() - 1;

        System.out.println("інвентар на обліку: ");
        showItems();

        System.out.println("введіть номер елементу, який потрібно відредагувати");
        itemIndex = validateInt(0, max);
        System.out.println("введіть номер властивості: 0 - inventoryNum, 1 - name, 2 - count, 3 - description");
        propertyIndex = validateInt(0, 3);

        switch (propertyIndex)
        {
            case 0:
            {
                System.out.print("введіть новий інтвентаризаційний номер: ");
                items.get(itemIndex).setInventoryNum(scanner.nextLine());
                break;
            }
            case 1:
            {
                System.out.print("введіть нову назву елементу: ");
                items.get(itemIndex).setName(scanner.nextLine());
                break;
            }
            case 3:
            {
                System.out.print("введіть новий опис: ");
                items.get(itemIndex).setDescription(scanner.nextLine());
                break;
            }
            case 2:
            {
                System.out.print("введіть нову кількість елементу: ");
                while(!scanner.hasNextInt())
                {
                    System.out.print("ви ввели неправильне число, спробуйте ще раз: ");
                    scanner.nextLine();
                }
                items.get(itemIndex).setCount(scanner.nextInt());
                scanner.nextLine();
                break;
            }
        }
        System.out.println("елемент відредаговано");
        serializeItems(items);
    }
}
