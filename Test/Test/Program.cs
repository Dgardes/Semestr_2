using System.Text;

namespace Лабораторна_робота_37
{
  internal class Student
  {

    public string Name { get; set; }

    public Student(string name)
    {
      Name = name;
    }
  }

  class Question
  {
    public string Text { get; set; }
    public string CorrectAnswer { get; set; }
    public int Points { get; set; }
    public Question(string text, string correctAnswer, int points)
    {
      Text = text;
      CorrectAnswer = correctAnswer;
      Points = points;
    }
  }

  class Result
  {
    public int Score { get; set; }
    public bool Passed { get; set; }

    public Result(int score, bool passed)
    {
      Score = score;
      Passed = passed;
    }
  }

  class ExamService
  {
    public Result StartExam(List<Question> questions)
    {
      int totalScore = 0;

      foreach (var question in questions)
      {
        Console.WriteLine(question.Text);

        string answer = Console.ReadLine();
        
        Console.WriteLine(answer);

        if (answer.Equals(question.CorrectAnswer, StringComparison.OrdinalIgnoreCase))
        {
          totalScore += question.Points;
        }
      }

      bool passed = totalScore >= 60;

      return new Лабораторна_робота_37.Result(totalScore, passed);
    }
  }

  class NotificationService
  {
    public void ShowResult(Result result)
    {
      Console.WriteLine($"\nНабрано балів: {result.Score}");

      if (result.Passed)
      {
        Console.WriteLine("Іспит складено");
      }
      else
      {
        Console.WriteLine("Іспит не складено");
      }
    }
  }

  internal class Program
  {

    static void Main(string[] args)
    {
        Console.InputEncoding = System.Text.Encoding.Unicode;
        Console.OutputEncoding = System.Text.Encoding.Unicode;

        Student student = new Student("Іван");

      Console.WriteLine($"Студент {student.Name} розпочав іспит\n");

      List<Question> questions = new List<Question>()
      {
        new Question("Столиця Грузії?", "Тбілісі", 30),

        new Question("Чому дорівнює 5! ?", "120", 30),

        new Question("Ким є Аїд?", "бог", 40)
      };

      ExamService examService = new ExamService();

      Result result = examService.StartExam(questions);

      NotificationService notification = new NotificationService();

      notification.ShowResult(result);

      Console.ReadKey();
    }
  }


}