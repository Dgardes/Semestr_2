package Part_2;

import java.util.HashMap;
import java.util.Map;

public class Main
{
    public static void main(String[] args)
    {

        MyHashMap myMap = new MyHashMap();

        Map<String, String> mapa = new HashMap<String, String>();
        mapa.put("0", "t");
        mapa.put("1", "f");
        mapa.put("2", "f");

        System.out.println(mapa.get("0"));
        System.out.println(mapa.get("1"));

    }
}
