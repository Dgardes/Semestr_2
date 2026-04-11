Main();

static void Main()
{
    Console.WriteLine("Розрахунок калорійності підтримки (формула Міффліна-Сан Жеора)\n");

    double weight = ReadDouble("Введіть вагу (кг): ", 1, 500);
    double height = ReadDouble("Введіть зріст (см): ", 50, 300);
    int age = ReadInt("Введіть вік: ", 5, 120);
    string gender = ReadGender();

    double bmr = CalculateBMR(weight, height, age, gender);

    Console.WriteLine($"\nВаша калорійність підтримки (BMR): {bmr:F2} ккал/день");
}

static double CalculateBMR(double weight, double height, int age, string gender)
{
    if (gender == "M")
        return 10 * weight + 6.25 * height - 5 * age + 5;
    else
        return 10 * weight + 6.25 * height - 5 * age - 161;
}

static double ReadDouble(string message, double min, double max)
{
    double value;
    while (true)
    {
        Console.Write(message);
        if (double.TryParse(Console.ReadLine(), out value) && value >= min && value <= max)
            return value;

        Console.WriteLine("Некоректне значення. Спробуйте ще раз.");
    }
}

static int ReadInt(string message, int min, int max)
{
    int value;
    while (true)
    {
        Console.Write(message);
        if (int.TryParse(Console.ReadLine(), out value) && value >= min && value <= max)
            return value;

        Console.WriteLine("Некоректне значення. Спробуйте ще раз.");
    }
}

static string ReadGender()
{
    while (true)
    {
        Console.Write("Введіть стать (M - чоловік, F - жінка): ");
        string input = Console.ReadLine().ToUpper();

        if (input == "M" || input == "F")
            return input;

        Console.WriteLine("Некоректне значення. Спробуйте ще раз.");
    }
}