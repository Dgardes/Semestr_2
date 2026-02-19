package Part_1;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Main
{
    public static void main(String[] args)
    {
        Set<String> set = new HashSet<String>();
        set.add("Mama");
        set.add("Mila");
        set.add("Ramu");

        Iterator<String> iterator = set.iterator();
        while (iterator.hasNext())
        {
            String text = iterator.next();
            System.out.println(text);
        }
    }
}