package Part_2;

public class MyMath
{
    public static double getAtan(double angle)
    {
        return Math.atan(angle);
    }

    public static double getSinh(double value)
    {
        return Math.sinh(value);
    }

    public static double getTan(double angle)
    {
        return Math.tan(angle);
    }

    public static double angleToRad(double angle)
    {
        return Math.toRadians(angle);
    }

    public static double getE()
    {
        return Math.E;
    }

    public static double pow(double a , double b)
    {
        return Math.pow(a, b);
    }

    public static double getLog10(double a)
    {
        return Math.log10(a);
    }

    public static double getExpm1(double x)
    {
        return Math.expm1(x);
    }

    public static int getRandomInt(int min, int max)
    {
        return (int) (Math.random()*(min+max+1)) - min;
    }

    public static double getRandomDouble(double min, double max)
    {
        return Math.random()*(min+max) - min;
    }
}
