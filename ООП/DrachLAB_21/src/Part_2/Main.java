package Part_2;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Main
{
    public static void main(String[] args)
    {
        Date date_1, date_2;

        SimpleDateFormat formater = new SimpleDateFormat("yyyy.MM.dd");

        Calendar calendar = Calendar.getInstance();
        calendar.set(2020, 11, 5);
        date_1 = calendar.getTime();
        calendar.set(2025, 5, 6);
        date_2 = calendar.getTime();

        System.out.println("отримуємо поточну дату: ");
        Date dateNow = MyData.getDateNow();
        System.out.println(dateNow);
        System.out.println("форматований вивід: " + formater.format(dateNow));

        System.out.println("перевірка, чи раніше передана дата " + formater.format(date_1) + "ніж поточна дата");
        String result = (MyData.isEarlier(date_1)? "раніше поточної" : "пізніше поточної");
        System.out.println("дата " + formater.format(date_1) + " " + result);

        System.out.println("перевірка, чи пізніща передана дата " + formater.format(date_1) + "ніж поточна дата");
        result = (MyData.isLater(date_1)? "пізніше поточної" : "раніше поточної");
        System.out.println("дата " + formater.format(date_1) + " " + result);

        System.out.println("метод порівняння двох дат (за днями)");
        System.out.println("дати: дата_1 = " + formater.format(date_1) + " дата_2 = " + formater.format(date_2));
        MyData.compareDays(date_1, date_2);
    }
}
