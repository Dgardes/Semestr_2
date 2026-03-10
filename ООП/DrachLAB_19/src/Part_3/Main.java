package Part_3;

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

        System.out.println("Приведення рядку до масиву байтів");
        byte[] bytes = MyStringNEXT.stringToBytes(string1);
        for (byte b : bytes){System.out.print(b + ", ");}
        System.out.println("\n Приведення рядку до хеш коду");
        System.out.println(MyStringNEXT.getHashCode(string1));
        System.out.println("вилученя підрядку");
        System.out.println(MyStringNEXT.removeSubString(string1, 0, 4));
    }
}
