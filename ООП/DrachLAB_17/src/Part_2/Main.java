package Part_2;

import java.util.HashMap;
import java.util.Map;

public class Main
{
    public static void main(String[] args)
    {

        MyHashMap myMap = new MyHashMap();

        myMap.put("ID_1", "Олександр");
        myMap.put("ID_2", "Олег");
        myMap.put("ID_3", "Дмитро");
        myMap.put("ID_4", "Дмитро");
        myMap.put("ID_5", "Тетяна");

        System.out.println("Поточна колекція:");
        myMap.printMyHashMap();

        System.out.println("Пошук за значенням");
        // Пошук за ключем
        String name = myMap.findByKey("ID_2");
        System.out.println("знайдено: " + name);

        Map<String, String> results = myMap.findByValue("Марія");
        System.out.println("Знайдено: " + results);

        Map.Entry<String, String> firstEntry = myMap.findFirstByValue("Тетяна");
        if (firstEntry != null)
        {
            System.out.println("Перший знайдений: " + firstEntry.getKey() + ": " + firstEntry.getValue());
        }

        System.out.println("видалення");
        myMap.remove("ID_1");
        System.out.println("Після видалення:");
        myMap.printMyHashMap();

        System.out.println("розділення");
        HashMap<String, String>[] parts = myMap.slice(-1);
        System.out.println("Перша частина: " + parts[0]);
        System.out.println("Друга частина: " + parts[1]);

        System.out.println("Очищення");
        myMap.clear();
        System.out.println("Колекція після:");
        myMap.printMyHashMap();

    }
}
