package MyPack;

import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        Person oleg = new Person();
        String filename = "oleg.bin";
        String personData = "personData.txt";

        //запис у файл
        FileWriter fw = null;
        try {
             fw = new FileWriter(personData);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            fw.write(scanner.nextLine());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            fw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //читання з файлу
        FileReader fr = null;

        try {
            fr = new FileReader(personData);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        String personName = "";
        /*
        try {
            personName = fr.read();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        */

        try {
            fr.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        //читання за допомогою файл інпут стрім
        try {
            FileInputStream fis = new FileInputStream(personData);
            personName = new String(fis.readAllBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Було прочитано з файлу наступне: " + personName);

        oleg.setName(personName);
        oleg.setAge(50);

        //серіадізація
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename)))
        {
            oos.writeObject(oleg);
            System.out.println("об'єкт серіалзізовано");
        }
        catch (IOException e)
        { }

        //десеріалізація
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename)))
        {
            Person decodedPerson = (Person) ois.readObject();
            System.out.println("об'єкт десеріалзіовано: " + decodedPerson.toString());
        }
        catch (IOException | ClassNotFoundException e)
        { }
    }
}