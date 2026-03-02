package Part_1;

public class Print
{
    void print(String s) {
        if (s == null) {
            throw new NullPointerException("Exception: s is null!");
        }
        System.out.println("Inside method print: " + s);
    }
}
