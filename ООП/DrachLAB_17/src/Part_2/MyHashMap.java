package Part_2;

import java.util.HashMap;
import java.util.Map;

public class MyHashMap
{
    private Map<String, String> myMap = new HashMap<String, String>();

    public MyHashMap(){}

    public void put(String key, String value)
    {
        this.myMap.put(key, value);
    }

    public void printMyHashMap()
    {
        for(Map.Entry<String, String> pair : myMap.entrySet())
        {
            System.out.println(pair.getKey() + ": " + pair.getValue());
        }
    }

    public void clear()
    {
        myMap.clear();
    }

    public Map<String, String> findByKey(String key)
    {
        Map<String, String> mapa = new HashMap<String, String>(){Map.Entry<String, String> myMap; };
        return mapa;
    }

    public Map<String, String> findByValue(String value)
    {
        Map<String, String> mapa = new HashMap<String, String>();

        if(!myMap.containsValue(value)) { return null;}

        for(Map.Entry<String, String> pair : myMap.entrySet())
        {
            if(pair.getValue() == value)
            { mapa.put(pair.getKey(), pair.getValue()); }
        }
        return mapa;
    }

    public Map<String, String> findFirstByValue(String value)
    {
        Map<String, String> mapa = new HashMap<String, String>();

        if(!myMap.containsValue(value)) { return null;}

        for(Map.Entry<String, String> pair : myMap.entrySet())
        {
            if(pair.getValue() == value)
            { mapa.put(pair.getKey(), pair.getValue()); return mapa;}
        }
        return mapa;
    }



    @Override
    public String toString() {
        return "MyHashMap{" +
                "myMap=" + myMap +
                '}';
    }
}
