package Part_1;

public class Main
{
    public static void main(String[] args)
    {
        String gm = "Good morning";

        int index1 = gm.indexOf("morni");
        int index2 = gm.indexOf("Vertex");
        int index3 = gm.indexOf("Good morning", -2);
        int index4 = gm.indexOf("Good morning", 2);
        int index5 = gm.indexOf("Good morning", 999);

        System.out.println("Мы ищем 'morni' в строке " + gm + ". Индекс " + index1);
        System.out.println("Мы ищем 'Vertex' в строке " + gm + ". Индекс " + index2);
        System.out.println("Мы ищем 'Good morning' в строке " + gm + " начиная с индекса -2. Результат: " + index3);
        System.out.println("Мы ищем 'Good morning' в строке " + gm + " начиная с индекса 2. Результат: " + index4);
        System.out.println("Мы ищем 'Good morning' в строке " + gm + " начиная с индекса 999. Результат: " + index5);
    }
}