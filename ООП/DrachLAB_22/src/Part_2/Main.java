package Part_2;

public class Main
{
    public static void main(String[] args)
    {
        double angleDeg = 45.0;
        double rad = MyMath.angleToRad(angleDeg);
        System.out.println("кут у градусах: " + angleDeg + ", у радіанах: " + rad);
        System.out.println("тангенс: " + MyMath.getTan(rad));
        System.out.println("арктангенс 1: " + MyMath.getAtan(1.0));
        System.out.println("гіперболічний синус 1: " + MyMath.getSinh(1.0));

        System.out.println("число Е: " + MyMath.getE());
        System.out.println("2 у степені 10: " + MyMath.pow(2.0, 10.0));
        System.out.println("логарифм 100: " + MyMath.getLog10(100.0));
        System.out.println("експонента мінус 1 для x = 1: " + MyMath.getExpm1(1.0));

        int minI = 10, maxI = 20;
        double minD = 1.5, maxD = 5.5;

        System.out.println("випадкове ціле [" + minI + ", " + maxI + "]: " + MyMath.getRandomInt(minI, maxI));
        System.out.println("випадкове дробове [" + minD + ", " + maxD + "]: " + MyMath.getRandomDouble(minD, maxD));
    }
}
