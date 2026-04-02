package org.example;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class MySerializer
{
    private static String fileName;
    private static Gson gson;
    private static Type type;


    public static void serialize(ArrayList<Human> humans)
    {
        gson = new GsonBuilder().create();
        String toJson = gson.toJson(humans);
        FileWriter fw = null;

        try {
            fw = new FileWriter(fileName);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            fw.write(toJson);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            fw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static ArrayList<Human> deserialize()
    {
        BufferedReader bf = null;

        try {
            bf = new BufferedReader(new FileReader(fileName));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        type = new TypeToken<ArrayList<Human>>(){}.getType();

        ArrayList<Human> _humans = gson.fromJson(bf, type);

        //Human human = gson.fromJson(bf, Human.class);

        return _humans;
    }

    public static String getFileName()
    {
        return fileName;
    }

    public static void setFileName(String _fileName)
    {
        fileName = _fileName;
    }
}
