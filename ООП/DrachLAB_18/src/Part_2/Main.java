package Part_2;

public class Main
{
    public static void main(String[] args)
    {
        String example = "ЦЕЙ текст СТВОРЕНИЙ для ДЕМОНСТРАЦІЇ можливостей КЛАСУ";
        String example_2 = "lorem ipsum dolor sit amet";

        char[] chars = MyString.enterChars();
        String stringFromChars = MyString.charArrayToString(chars);
        System.out.println("Масив символів, об'єднаний у рядок " + stringFromChars);

        System.out.println("Початкові рядки: ");
        System.out.println(example);
        System.out.println(example_2);

        System.out.println("об'єднання рядків:");
        System.out.println("Результат:");
        System.out.println(MyString.mergeStrings(example, example_2));

        System.out.println("Об'єднання рядка и числа");
        int number = 123;
        System.out.println("Результат:");
        System.out.println(MyString.mergeStringAndInt(example, number));

        System.out.println("Приведення рядку до верхнього регістру");
        System.out.println("Результат:");
        System.out.println(MyString.stringToUpper(example));

        System.out.println("Приведення рядку до нижнього регістру");
        System.out.println("Результат:");
        System.out.println(MyString.stringToLover(example));

        System.out.println("Видалення пробілів з рядку");
        System.out.println("Результат:");
        System.out.println(MyString.clearWhiteSpace(example));

    }
}