package Part_2;

import java.text.DateFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MyData
{
    public static Date getDateNow()
    {
        return new Date();
    }

    public static String getFormatedDateNow()
    {
        SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd");
        return format.format(getDateNow());
    }

    public static boolean isEarlier(Date date1)
    {
        Date dat = new Date();
        return (date1.compareTo(dat) < 0)? true : false ;
    }

    public static boolean isLater(Date date1)
    {
        Date dat = new Date();
        return (date1.compareTo(dat) > 0)? true : false ;
    }

    public static void compareDays(Date date1, Date date2)
    {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date1);
        int dayOfFirst = calendar.get(Calendar.DAY_OF_MONTH);
        calendar.setTime(date2);
        int dayOfSecond = calendar.get(Calendar.DAY_OF_MONTH);

        if (dayOfFirst > dayOfSecond)
        { System.out.println("перша дата ближча до кінця місяця. День : " + dayOfFirst); }
        else if (dayOfFirst < dayOfSecond)
        { System.out.println("друга дата ближча до кінця місяця. День : " + dayOfSecond); }
        else if (dayOfFirst == dayOfSecond)
        { System.out.println("дні дат однакові. Дні : " + dayOfSecond); }
    }
}
