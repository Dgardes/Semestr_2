package Part_1;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args)
    {
        Print print = new Print();
        List<String> list= Arrays.asList("first step", null, "second step");
        for (String s : list)
        {
            try
            { print.print(s); }
            catch (NullPointerException e)
            {
                System.out.println(e.getMessage());
                System.out.println("Exception was processed. Program continues");
            }
            finally
            { System.out.println("Inside bloсk finally"); }
        }
    }
}

