package Part_2;

public class MyException
{
    public static void catchE(Exception e)
    {
        String ex = e.getClass().getSimpleName();
        System.out.println(ex);
        switch (ex)
        {
            case "ArithmeticException":
            {System.out.println("Спробу поділити на нуль відхилено"); break;}
            case "ArrayIndexOutOfBoundsException":
            {System.out.println("Вихід за межі масиву"); break;}
            case "ArrayStoreException":
            {System.out.println("Спроба зберегти невірний тип даних "); break;}
            default:
            {System.out.println("Не вдалося обробити помилку. Помилка: " + e.getMessage()); break;}
        }
    }
}
