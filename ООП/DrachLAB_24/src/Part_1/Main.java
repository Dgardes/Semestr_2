package Part_1;

import com.google.gson.Gson;

public class Main
{
    public static void main(String[] args)
    {
        Gson gson = new Gson ();
        gson.toJson( 123 ); // 123
        gson.toJson( "hello"); // "hello";
        gson.toJson(Long.valueOf( 10 )); // 10

        Integer integer = gson.fromJson( "1", int .class);
        String string = gson.fromJson( "world", String.class);
        Boolean bool = gson.fromJson( "true", Boolean.class);

        System.out.println("integer: "+ integer);
        System.out.println("string: " + string);
        System.out.println("bool: " + bool);
    }
}