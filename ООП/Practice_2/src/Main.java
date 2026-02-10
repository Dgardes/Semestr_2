import java.util.*;

public class Main
{
    public static void main(String[] args) {
//        Set<String> mySet = new HashSet<>();
//        mySet.add("A");
//        mySet.add("B");
//        mySet.add("C");
//        mySet.add("C");
//
//        System.out.println(mySet);
//
//        mySet.remove("A");
//
//        System.out.println(mySet);
//
//        System.out.println("Розмір: " + mySet.size());

        Map<Integer, String> student = new HashMap<>();
        student.put(1, "Oleg");
        student.put(2, "Ivan");
        student.put(3, "Sasha");

        System.out.println(student);
        student.put(3, "Ya");

        System.out.println(student);

        for (Map.Entry<Integer, String> item : student.entrySet())
        {
            System.out.println(item.getKey() + ": " + item.getValue());
        }

        student.remove(3);
        System.out.println(student.get(2));
        System.out.println(student.containsValue("Ivan"));
        System.out.println(student.containsKey(3));

        if(!student.isEmpty())
        {
            System.out.println("колекція не пуста");
        }

        student.clear();
        System.out.println(student);
    }
}
