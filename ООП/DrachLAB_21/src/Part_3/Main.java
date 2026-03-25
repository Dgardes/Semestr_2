package Part_3;

import java.time.LocalDate;
import java.time.Period;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class Main
{
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);

        while(true)
        {
            Date date;
            int year, month, day;

            System.out.println("Введіть рік: ");
            while (true)
            {
                if (scanner.hasNextInt())
                {
                    year = scanner.nextInt();
                    if (year > 0) break;
                }
                else
                { scanner.next(); }
                System.out.println("Рік введено неправильно, спробуйте ще раз: ");
            }

            System.out.println("Введіть місяць: ");
            while (true)
            {
                if (scanner.hasNextInt())
                {
                    month = scanner.nextInt();
                    if (month >= 1 && month <= 12) break;
                }
                else
                { scanner.next(); }
                System.out.println("Рік введено неправильно, спробуйте ще раз: ");
            }

            System.out.println("Введіть день: ");
            while (true)
            {
                if (scanner.hasNextInt())
                {
                    day = scanner.nextInt();
                    if (day >= 1 && day <= 31) break;
                }
                else
                { scanner.next(); }
                System.out.println("Рік введено неправильно, спробуйте ще раз: ");
            }

            Calendar calendar = Calendar.getInstance();
            calendar.setLenient(false);

            try
            {
                calendar.set(year, month, day);
                date = calendar.getTime();
            }
            catch (Exception e)
            {
                System.out.println("Ви ввели неіснуючу дату. введіть дату ще раз");
                continue;
            }

            Zodiac zodiac1 = new Zodiac(date);
            Zodiac zodiac2 = new Zodiac(year, month, day);
            break;
        }
    }
}
