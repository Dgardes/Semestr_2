package Part_2;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main
{
    public static void main(String[] args)
    {
        try
        { int makeEx = 1 / 0; }
        catch (Exception e)
        { MyException.catchE(e); }

        try
        {
            Object[] arr = new String[1];
            arr[0] = 123;
        }
        catch (Exception e)
        { MyException.catchE(e); }

        try
        {
            Object[] arr = new Integer[1];
            arr[2] = 123;
        }
        catch (Exception e)
        { MyException.catchE(e); }

        try
        {
            Scanner scanner = new Scanner(System.in);
            int makeEx = scanner.nextInt();
        }
        catch (Exception e)
        { MyException.catchE(e); }
    }
}
