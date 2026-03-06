package Part_2;

import Part_1.MyStringNEXT;

public class Main
{
    public static void main(String[] args)
    {
        String string1 = "Good morning";
        String string2 = "Good morning";
        String string3 = "Good night";

        System.out.println("Рядки, над якими будуть проводитись операції");
        System.out.println(string1);
        System.out.println(string2);
        System.out.println(string3);

        System.out.println("Порівняння рядків: ");

        System.out.println( string1 + " і " + string2 + " резлуьтат: " + MyStringNEXT.compareStrings(string1, string2));
        System.out.println( string1 + " і " + string3 + " резлуьтат: " + MyStringNEXT.compareStrings(string1, string3));

        System.out.println("Пошук символу за індексом:");
        System.out.println(MyStringNEXT.findChar(string1, 3));

        System.out.println("Пошук символу починаючи із зазначенного індексу");
        System.out.println(MyStringNEXT.extendedFindChar(string2, 4, 2));

        System.out.println("Пошук підрядка з певного індексу");
        System.out.println(MyStringNEXT.findSubString(string2, 4));

        System.out.println("Пошук підрядка у діапазоні");
        System.out.println(MyStringNEXT.findSubString(string2, 4, 15));
    }
}
