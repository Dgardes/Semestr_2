package Part_3;

public class MyTemplate
{
    private String template;
    public MyTemplate(String firstName, String lastName, String fatherName)
    {
        template = String.format(
            "------------------------------------------------------------------------------\n" +
            "Заява на отримання підвишення стипендії\n" +
            "Я, %s %s %s, звертаюся за проханням розглянути мою кандидатуру\n" +
            "на отримання додаткової стипендії на підставі гарної академічної успішності,\n" +
            "активності у життєдіяльності навчального закладу та позанавчальні досягнення.\n" +
            "Дата:____________                            Підпис:____________\n" +
            "------------------------------------------------------------------------------\n",
            lastName, firstName, fatherName
        );
    }

    public String  getTemplate()
    {
        return template;
    }
}
