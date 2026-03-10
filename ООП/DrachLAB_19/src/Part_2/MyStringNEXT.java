package Part_2;

public class MyStringNEXT
{
    public static int compareStrings(String string1, String string2)
    {
        return (string1.equals(string2))? 0 : -1;
    }

    public static char findChar(String string1, int index)
    {
        try
        {
            return string1.charAt(index);
        }
        catch(Exception e)
        {
            System.out.println("індекс вийшов за межі масиву символів. Повернено '0'");
            return 0;
        }
    }

    public static char extendedFindChar(String string1, int charIndex, int startIndex)
    {
        if (startIndex >= string1.length())
        {
            System.out.println("Індекс, з якого починається перевірка, більший за розмір рядку. Повернено 0");
            return 0;
        }
        if( startIndex > charIndex)
        {
            System.out.println("Індекс, з якого починається перевірка, більший за перевірюємий. Повернено 0");
            return 0;
        }
        return string1.charAt(charIndex);
    }

    public static String findSubString(String string1, int startIndex)
    {
        if (startIndex > string1.length())
        {
            System.out.println("Індекс, з якого починається ппошук, більший за розмір рядку. Повернено null");
            return null;
        }
        return string1.substring(startIndex);
    }

    public static String findSubString(String string1, int startIndex, int endIndex)
    {
        if (startIndex > string1.length())
        {
            System.out.println("Індекс, з якого починається пошук, більший за розмір рядку. Повернено null");
            return null;
        }
        if (startIndex > endIndex)
        {
            System.out.println("Індекс, з якого починається пошук, більший за граничне значення пошуку. Повернено null");
            return null;
        }
        if (endIndex > string1.length())
        {
            endIndex = string1.length();
        }
        return string1.substring(startIndex, endIndex);
    }





}
