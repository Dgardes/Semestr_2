package Part_3;

import java.time.LocalDate;
import java.time.Period;
import java.util.*;

public class Zodiac
{
    public Zodiac(int year, int month, int day)
    {
        calcSlovZodiac(month, day);
        calcChineseZodiac(year);
    }

    public Zodiac(Date date)
    {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        calcSlovZodiac(calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        calcChineseZodiac(calendar.get(Calendar.YEAR));
    }

    private void calcSlovZodiac(int month, int day)
    {
        String result = "";
        int index = month * 100 + day;

        if (index >= 120 && index <= 218) result = "Водолій";
        if (index >= 219 && index <= 320) result = "Риби";
        if (index >= 321 && index <= 419) result =  "Овен";
        if (index >= 420 && index <= 520) result = "Телець";
        if (index >= 521 && index <= 620) result = "Близнюки";
        if (index >= 621 && index <= 722) result = "Рак";
        if (index >= 723 && index <= 822) result = "Лев";
        if (index >= 823 && index <= 922) result = "Діва";
        if (index >= 923 && index <= 1022) result = "Терези";
        if (index >= 1023 && index <= 1121) result = "Скорпіон";
        if (index >= 1122 && index <= 1221) result = "Стрілець";
        if (index >= 1222 || index <= 119) result = "Козоріг";

        System.out.println("Ваш знак зодіаку за слов'янським календаерм: " + result);
    }

    private void calcChineseZodiac(int year)
    {
        String result = "";

        if (year % 12 == 0) result = "Мавпа";
        if (year % 12 == 1) result = "Півень";
        if (year % 12 == 2) result =  "Собака";
        if (year % 12 == 3) result = "Свиня";
        if (year % 12 == 4) result = "Щур";
        if (year % 12 == 5) result = "Бик";
        if (year % 12 == 6) result = "Тигр";
        if (year % 12 == 7) result = "Кролик";
        if (year % 12 == 8) result = "Дракон";
        if (year % 12 == 9) result = "Змія";
        if (year % 12 == 10) result = "Кінь";
        if (year % 12 == 11) result = "Коза";

        System.out.println("Ваш знак зодіаку за китайським календаерм: " + result);
    }

}
