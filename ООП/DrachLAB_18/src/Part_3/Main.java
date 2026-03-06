package Part_3;

public class Main
{
    public static void main(String[] args)
    {
        String firstName = "Олег";
        String secondName = "Шуба";
        String fatherName = "Іванович";

        MyTemplate template = new MyTemplate(firstName,secondName,fatherName);
        System.out.println(template.getTemplate());
    }
}
