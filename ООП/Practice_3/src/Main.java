import java.util.*;

public class Main
{
    public static void main(String[] args)
    {
        Set<Integer> mySet = new HashSet<>();
        mySet.add(1);
        mySet.add(2);
        mySet.add(3);

        for(Integer it : mySet)
        {
            System.out.print(it.toString() + " ");
        }

        Map<Integer, String> myMap = new HashMap<>();
        myMap.put(1, "one");
        myMap.put(2, "two");
        myMap.put(3, "three");

        for(Map.Entry<Integer, String> iter : myMap.entrySet())
        {
            System.out.println(iter.getKey() + " " + iter.getValue());
        }

        if (myMap.containsValue("one"))
        { System.out.println("the value \"one\" is available"); }

        if (myMap.containsKey(2))
        { System.out.println("the key \"2\" is available"); }



    }

}