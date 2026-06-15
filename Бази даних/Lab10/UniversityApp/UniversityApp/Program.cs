using System;
using MySql.Data.MySqlClient;

class Program
{
    static void Main()
    {
        Console.OutputEncoding = System.Text.Encoding.UTF8;
        Console.InputEncoding = System.Text.Encoding.UTF8;

        string connectionString =
            "server=localhost;user=root;password=Admin;database=university_db;CharSet=utf8mb4;";

        using (MySqlConnection conn = new MySqlConnection(connectionString))
        {
            try
            {
                conn.Open();
                Console.WriteLine("+ Підключення до бази даних успішне!");
                Console.WriteLine(new string('-', 50));

                Console.WriteLine("=== Дані з VIEW student_courses ===");

                string query = "SELECT * FROM student_courses;";

                using (MySqlCommand cmd = new MySqlCommand(query, conn))
                using (MySqlDataReader reader = cmd.ExecuteReader())
                {
                    while (reader.Read())
                    {
                        Console.WriteLine(
                            "Студент: " + reader["name"] +
                            " | Курс: " + reader["course_name"] +
                            " | Оцінка: " + reader["grade"]
                        );
                    }
                }

                Console.WriteLine(new string('-', 50));

                Console.WriteLine("=== Виклик процедури add_student ===");

                MySqlCommand cmdProcedure = new MySqlCommand("add_student", conn);
                cmdProcedure.CommandType = System.Data.CommandType.StoredProcedure;

                cmdProcedure.Parameters.AddWithValue("@p_name", "Олег Савчук");
                cmdProcedure.Parameters.AddWithValue("@p_age", 23);

                cmdProcedure.ExecuteNonQuery();

                Console.WriteLine("Процедура виконана успішно (додано Олег Савчук).");

                Console.WriteLine(new string('-', 50));

                Console.WriteLine("=== Перевірка тригера (log_table) ===");

                string logQuery = "SELECT * FROM log_table ORDER BY id DESC LIMIT 5;";

                using (MySqlCommand logCmd = new MySqlCommand(logQuery, conn))
                using (MySqlDataReader logReader = logCmd.ExecuteReader())
                {
                    while (logReader.Read())
                    {
                        Console.WriteLine(
                            logReader["id"] +
                            " | " + logReader["action"] +
                            " | " + logReader["time"]
                        );
                    }
                }

                Console.WriteLine(new string('-', 50));

                Console.WriteLine("=== Оновлений список students ===");

                string studentsQuery = "SELECT * FROM students;";

                using (MySqlCommand stCmd = new MySqlCommand(studentsQuery, conn))
                using (MySqlDataReader stReader = stCmd.ExecuteReader())
                {
                    while (stReader.Read())
                    {
                        Console.WriteLine(
                            "ID: " + stReader["id"] +
                            " | Ім'я: " + stReader["name"] +
                            " | Вік: " + stReader["age"]
                        );
                    }
                }
            }
            catch (Exception ex)
            {
                Console.WriteLine("- Помилка: " + ex.Message);
            }
        }

        Console.WriteLine("\nНатисніть будь-яку клавішу для завершення...");
        Console.ReadKey();
    }
}