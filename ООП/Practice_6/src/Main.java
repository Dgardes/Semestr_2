import java.io.*;

public class Main {
    public static void main(String[] args) {

        Person oleg = new Person("Oleg", 50);
        String filename = "oleg.bin";

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