package Part_2;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Scanner;

public class Main
{
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        String fileName = "P2_MyOwl.xml";

        String type, name, gender;
        double wingSpan, weight, speed;

        System.out.print("Введіть вид сови: "); type = scanner.nextLine();
        System.out.print("Введіть ім'я сови: "); name = scanner.nextLine();
        System.out.print("Введіть гендер сови: "); gender = scanner.nextLine();
        System.out.print("Введіть розмах крил сови (double): "); wingSpan = validateDouble(scanner);
        System.out.print("Введіть вагу сови (double): "); weight = validateDouble(scanner);
        System.out.print("Введіть швидкість сови (double): "); speed = validateDouble(scanner);

        Owl myOwl = new Owl(type, name, gender, wingSpan, weight, speed);

        //серіалізація:
        try
        {
            XMLEncoder encoder = new XMLEncoder(new FileOutputStream(fileName));
            encoder.writeObject(myOwl);
            encoder.close();
        }
        catch (FileNotFoundException e) { throw new RuntimeException(e); }

        //десеріалізація:
        Owl decodedOwl = null;
        try
        {
            XMLDecoder decoder = new XMLDecoder(new BufferedInputStream(new FileInputStream(fileName)));
             decodedOwl = (Owl) decoder.readObject();
            decoder.close();
        }
        catch (FileNotFoundException e) { throw new RuntimeException(e); }

        System.out.println("декодовоаний об'єкт: ");
        System.out.println(decodedOwl.toString());
    }

    public static double validateDouble(Scanner scanner)
    {
        while(!scanner.hasNextDouble())
        {
            System.out.println("ви ввели не число, спробуйте ше раз");
            scanner.nextLine();
        }
        return scanner.nextDouble();
    }

}