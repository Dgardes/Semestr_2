public class MyRandom
{
    public static int getRandom(int min, int max)
    {
        return (int) Math.round(Math.random() * (max - min + 1)) + min;
    }
}
