package org.example;

import com.google.gson.Gson;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args)
    {
        ArrayList<Human> humans = new ArrayList<>();
        Human h1 = new Human("Oleg", 41);
        Human h2 = new Human("Ivan", 14);

        humans.add(h1);
        humans.add(h2);

        MySerializer.setFileName("humans.json");
        System.out.println(humans);
        MySerializer.serialize(humans);

        ArrayList<Human> humans_2 = MySerializer.deserialize();
        System.out.println("десеріалізовані люди: " + humans_2);

    }
}