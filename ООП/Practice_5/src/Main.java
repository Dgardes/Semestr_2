
public class Main {
    public static void main(String[] args) {

        double firstNum = -15.5;
        double secondNum = Math.abs(firstNum);

        System.out.println(firstNum);
        System.out.println(secondNum);

        System.out.println((int) Math.ceil(secondNum)); //округлення вгору
        System.out.println((int) Math.floor(secondNum)); //округлення вниз
        System.out.println((int) Math.round(secondNum)); // округлення

        System.out.println("два числа + порівняння");
        int randomNum_1 = MyRandom.getRandom(1, 5);
        int randomNum_2 = MyRandom.getRandom(1, 5);
        System.out.println(randomNum_1);
        System.out.println(randomNum_2);

        System.out.println(Math.max(randomNum_1, randomNum_2));

        System.out.println("Розділення колоди");
        int cardHeap = 36;
        int people = 5;
        int divideHeap = Math.floorDiv(cardHeap, people);
        System.out.println(divideHeap);

        int test = (int) Math.round(Math.random() * (80 - 20 + 1) + 20);
        System.out.println("число: " + test);

        int loopsWithout80 = 0;

        do
        {
            int randRand = (int) Math.round(Math.random() * (80 - 20 + 1) + 20);
            if (randRand != 80)
            { loopsWithout80 ++; }
            else
            { System.out.println("loopsWithout80: " + loopsWithout80); break; }
        }
        while(true);

        System.out.println("Логорифми");
        System.out.println(Math.log10(8) / Math.log10(2));
        System.out.println("Округлення");
        System.out.println((int) Math.rint(2.51));
    }
}