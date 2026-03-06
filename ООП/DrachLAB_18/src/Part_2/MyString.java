package Part_2;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MyString
{
    public static char[] enterChars()
    {
        Scanner scanner = new Scanner(System.in);
        List<Character> chars = new ArrayList<Character>();
        while(true)
        {
            char ch = scanner.next().charAt(0);
            chars.add(ch);
            if(ch == '0')
            { break; }
        }

        char[] charsToRerturn =  new char[chars.size()];
        for(int i = 0; i < chars.size(); i++)
        {
            charsToRerturn[i] = chars.get(i);
        }
        scanner.close();
        return charsToRerturn;
    }

    public static String charArrayToString(char[] chars)
    {
        return new String(chars);
    }

    public static String mergeStrings(String string1, String string2)
    {
        return string1.concat(string2);
    }

    public static String mergeStringAndInt(String string1, int int1)
    {
        Object int2 = (Object) int1;
        Object string2 = (Object) string1;
        return (String) string2 + int2;
    }

    public static String stringToLover(String string)
    {
        return string.toLowerCase();
    }

    public static String stringToUpper (String string)
    {
        return string.toUpperCase();
    }

    public static String clearWhiteSpace(String string)
    {
        return string.replace(" ", "");
    }
}
