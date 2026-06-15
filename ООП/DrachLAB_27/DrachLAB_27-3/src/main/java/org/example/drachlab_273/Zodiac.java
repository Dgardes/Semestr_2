package org.example.drachlab_273;

import java.util.Calendar;
import java.util.Date;

public class Zodiac {
    private String slavicZodiac;
    private String chineseZodiac;

    public Zodiac(int year, int month, int day) {
        this.slavicZodiac = calcSlovZodiac(month, day);
        this.chineseZodiac = calcChineseZodiac(year);
    }

    public Zodiac(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        this.slavicZodiac = calcSlovZodiac(calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.DAY_OF_MONTH));
        this.chineseZodiac = calcChineseZodiac(calendar.get(Calendar.YEAR));
    }

    public String getSlavicZodiac() { return slavicZodiac; }
    public String getChineseZodiac() { return chineseZodiac; }

    private String calcSlovZodiac(int month, int day) {
        String result = "";
        int index = month * 100 + day;

        if (index >= 120 && index <= 218) result = "Водолій";
        else if (index >= 219 && index <= 320) result = "Риби";
        else if (index >= 321 && index <= 419) result = "Овен";
        else if (index >= 420 && index <= 520) result = "Телець";
        else if (index >= 521 && index <= 620) result = "Близнюки";
        else if (index >= 621 && index <= 722) result = "Рак";
        else if (index >= 723 && index <= 822) result = "Лев";
        else if (index >= 823 && index <= 922) result = "Діва";
        else if (index >= 923 && index <= 1022) result = "Терези";
        else if (index >= 1023 && index <= 1121) result = "Скорпіон";
        else if (index >= 1122 && index <= 1221) result = "Стрілець";
        else if (index >= 1222 || index <= 119) result = "Козоріг";

        return result;
    }

    private String calcChineseZodiac(int year) {
        String result = "";

        if (year % 12 == 0) result = "Мавпа";
        else if (year % 12 == 1) result = "Півень";
        else if (year % 12 == 2) result = "Собака";
        else if (year % 12 == 3) result = "Свиня";
        else if (year % 12 == 4) result = "Щур";
        else if (year % 12 == 5) result = "Бик";
        else if (year % 12 == 6) result = "Тигр";
        else if (year % 12 == 7) result = "Кролик";
        else if (year % 12 == 8) result = "Дракон";
        else if (year % 12 == 9) result = "Змія";
        else if (year % 12 == 10) result = "Кінь";
        else if (year % 12 == 11) result = "Коза";

        return result;
    }
}