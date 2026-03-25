package Part_1;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Main
{
    public static void main(String[] args)
    {
        GregorianCalendar calendar = new GregorianCalendar(2017, Calendar.JANUARY, 25);

        calendar.set(Calendar.HOUR, 10);
        calendar.set(Calendar.MINUTE, 42);
        calendar.set(Calendar.SECOND, 12);

        System.out.println("Рік: " + calendar.get(Calendar.YEAR));
        System.out.println("Місяць: " + calendar.get(Calendar.MONTH));
        System.out.println("Порядковий номер тижня в місяці: " + calendar.get(Calendar.WEEK_OF_MONTH));
        System.out.println("Число: " + calendar.get(Calendar.DAY_OF_MONTH));
        System.out.println("Години: " + calendar.get(Calendar.HOUR));
        System.out.println("Хвилини: " + calendar.get(Calendar.MINUTE));
        System.out.println("Секунди: " + calendar.get(Calendar.SECOND));
        System.out.println("Мілісекунди: " + calendar.get(Calendar.MILLISECOND));
    }
}