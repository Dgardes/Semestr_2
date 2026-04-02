package Practice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.*;

public class Main
{
    public static void main(String[] args) throws FileNotFoundException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);

        Person pers = new Person("oleg", 51);

        System.out.println(args[0]);
        //серіалізація
        String toJson = "";
        try {
            toJson = mapper.writeValueAsString(pers);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        System.out.println(toJson);

        //десеріалізація
        mapper = new ObjectMapper();
        Person person = null;

        try {
            person = mapper.readValue(toJson, Person.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        System.out.println(person.toString());

        //серіалізація у файл
        FileWriter fw = null;
        try {
            fw = new FileWriter("testPerson.json");
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

        //отримання з файлу
        mapper = new ObjectMapper();
        Person person_2 = null;

        try {
            person_2 = mapper.readValue(new File("testPerson.json"), Person.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println(person_2.toString());
    }
}