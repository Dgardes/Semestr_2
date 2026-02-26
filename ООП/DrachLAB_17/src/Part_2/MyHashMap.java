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

    public String findByKey(String key)
    {
        return myMap.get(key);
    }

    public Map<String, String> findByValue(String value)
    {
        Map<String, String> mapa = new HashMap<String, String>();

        if(!myMap.containsValue(value)) { return null;}

        for(Map.Entry<String, String> pair : myMap.entrySet())
        {
            if(pair.getValue().equals(value))
            { mapa.put(pair.getKey(), pair.getValue()); }
        }
        return mapa;
    }

    public Map.Entry<String, String> findFirstByValue(String value)
    {
        if(!myMap.containsValue(value))
        { return null;}

        for(Map.Entry<String, String> pair : myMap.entrySet())
        {
            if(pair.getValue().equals(value))
            { return pair; }
        }
        return null;
    }

    public HashMap<String, String>[] slice(int nextMapIdx)
    {
        HashMap<String, String>[] maps = new HashMap[]{new HashMap<>(), new HashMap<>()};
        int mapSize = myMap.size();
        int counter = 0;
        if(nextMapIdx == -1)
        {
            for(Map.Entry<String, String> entry : myMap.entrySet())
            {
                if (counter <= mapSize / 2)
                {maps[0].put(entry.getKey(), entry.getValue()); }
                else {maps[1].put(entry.getKey(), entry.getValue()); }
                counter++;
            }
        }
        else
        {
            for(Map.Entry<String, String> entry : myMap.entrySet())
            {
                if (counter <= nextMapIdx)
                {maps[0].put(entry.getKey(), entry.getValue()); }
                else {maps[1].put(entry.getKey(), entry.getValue()); }
                counter++;
            }
        }

        return maps;
    }

    public void remove(String key)
    {
        myMap.remove(key);
    }

    @Override
    public String toString() {
        return "MyHashMap{" +
                "myMap=" + myMap +
                '}';
    }
}
