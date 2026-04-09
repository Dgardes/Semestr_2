using System.Text;

Console.OutputEncoding = Encoding.UTF8;
Console.WriteLine("=== Розрахунок норми калорій (Міффлін-Сан Жеор) ===");

// Зчитування даних
double vaha = ReadDouble("Введіть вашу вагу (кг): ");
double zrist = ReadDouble("Введіть ваш зріст (см): ");
int vik = (int)ReadDouble("Введіть ваш вік: ");

Console.WriteLine("Ваша стать (Ч/Ж):");
string gender = Console.ReadLine()?.ToUpper() ?? "";
while (gender != "Ч" && gender != "Ж")
{
    Console.Write("Будь ласка, введіть 'Ч' для чоловіків або 'Ж' для жінок: ");
    gender = Console.ReadLine()?.ToUpper() ?? "";
}

// Розрахунок BMR
double bmr = CalculateBMR(vaha, zrist, vik, gender);

Console.WriteLine("\nОберіть рівень активності (1-5):");
Console.WriteLine("1. Мінімальний, 2. Низький, 3. Середній, 4. Високий, 5. Екстремальний");

double factor = GetActivityFactor();
double totalCalories = bmr * factor;

Console.WriteLine($"\nВаш базовий метаболізм (BMR): {bmr:F0} ккал.");
Console.WriteLine($"Добова норма для підтримки ваги: {totalCalories:F0} ккал.");

// Локальні методи
static double CalculateBMR(double w, double h, int a, string g)
{
    // Змінено назву змінної з 'base' на 'resultValue', щоб уникнути конфлікту
    double resultValue = (10 * w) + (6.25 * h) - (5 * a);
    return g == "Ч" ? resultValue + 5 : resultValue - 161;
}

static double ReadDouble(string message)
{
    double res;
    Console.Write(message);
    while (!double.TryParse(Console.ReadLine(), out res) || res <= 0)
    {
        Console.Write("Помилка! Введіть число більше 0: ");
    }
    return res;
}

static double GetActivityFactor()
{
    while (true)
    {
        string input = Console.ReadLine() ?? "";
        if (input == "1") return 1.2;
        if (input == "2") return 1.375;
        if (input == "3") return 1.55;
        if (input == "4") return 1.725;
        if (input == "5") return 1.9;
        Console.Write("Оберіть варіант від 1 до 5: ");
    }
}