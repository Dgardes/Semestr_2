using System;

class Program
{
    static void Main()
    {
        Console.WriteLine("Розрахунок калорійності підтримки (формула Міффліна-Сан Жеора)");

        double weight = ReadPositiveDouble("Введіть вагу (кг): ");
        double height = ReadPositiveDouble("Введіть зріст (см): ");
        int age = (int)ReadPositiveDouble("Введіть вік (роки): ");
        string gender = ReadGender("Введіть стать (ч/ж): ");

        double bmr = CalculateBMR(weight, height, age, gender);
        Console.WriteLine($"Ваш базовий рівень метаболізму (BMR): {bmr:F2} ккал/день");

        double activity = ReadActivityFactor();
        double maintenanceCalories = bmr * activity;
        Console.WriteLine($"Калорійність підтримки: {maintenanceCalories:F2} ккал/день");
    }

    static double ReadPositiveDouble(string message)
    {
        double value;
        do
        {
            Console.Write(message);
            string input = Console.ReadLine();
            if (double.TryParse(input, out value) && value > 0)
                return value;
            Console.WriteLine("Помилка: введіть додатне число.");
        } while (true);
    }

    static string ReadGender(string message)
    {
        string input;
        do
        {
            Console.Write(message);
            input = Console.ReadLine()?.Trim().ToLower();
            if (input == "ч" || input == "ж")
                return input;
            Console.WriteLine("Помилка: введіть 'ч' для чоловіка або 'ж' для жінки.");
        } while (true);
    }

    static double CalculateBMR(double weight, double height, int age, string gender)
    {
        if (gender == "ч")
            return 10 * weight + 6.25 * height - 5 * age + 5;
        else
            return 10 * weight + 6.25 * height - 5 * age - 161;
    }

    static double ReadActivityFactor()
    {
        Console.WriteLine("Оберіть рівень активності:");
        Console.WriteLine("1. Мінімальна (сидячий спосіб життя) – 1.2");
        Console.WriteLine("2. Легка активність – 1.375");
        Console.WriteLine("3. Середня активність – 1.55");
        Console.WriteLine("4. Висока активність – 1.725");
        Console.WriteLine("5. Дуже висока активність – 1.9");

        int choice;
        do
        {
            Console.Write("Ваш вибір (1-5): ");
            string input = Console.ReadLine();
            if (int.TryParse(input, out choice) && choice >= 1 && choice <= 5)
                break;
            Console.WriteLine("Помилка: введіть число від 1 до 5.");
        } while (true);

        switch (choice)
        {
            case 1: return 1.2;
            case 2: return 1.375;
            case 3: return 1.55;
            case 4: return 1.725;
            case 5: return 1.9;
            default: return 1.2;
        }
    }
}
