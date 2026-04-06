package Part_3;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class BicycleCatalog
{
    private ArrayList<Bicycle> bicycles = new ArrayList<>();
    private Gson gson;

    public BicycleCatalog(ArrayList<Bicycle> bicycles)
    {
        this.bicycles = bicycles;
        this.gson = new Gson();
    }

    public void addBicycle(Bicycle bicycle) {
        bicycles.add(bicycle);
    }

    public String serialize()
    {
        return gson.toJson(bicycles);
    }

    public ArrayList<Bicycle> deserialize(String json)
    {
        Type listType = new TypeToken<ArrayList<Bicycle>>(){}.getType();
        this.bicycles = gson.fromJson(json, listType);
        return this.bicycles;
    }

    public Bicycle findById(int id)
    {
        for (Bicycle bicycle : bicycles) {
            if (bicycle.getID() == id) {
                return bicycle;
            }
        }
        return null;
    }

    public ArrayList<Bicycle> findByBrand(String brand)
    {
        ArrayList<Bicycle> result = new ArrayList<>();
        for (Bicycle bicycle : bicycles) {
            if (bicycle.getBrand().equalsIgnoreCase(brand)) {
                result.add(bicycle);
            }
        }
        return result;
    }

    public void saveToFile(String fileName) {
        try (FileWriter writer = new FileWriter(fileName)) {
            gson.toJson(bicycles, writer);
            System.out.println("Дані успішно збережено у файл: " + fileName);
        } catch (IOException e) {
            System.err.println("Помилка при записі у файл: " + e.getMessage());
        }
    }

    public void loadFromFile(String fileName) {
        try (FileReader reader = new FileReader(fileName)) {
            Type listType = new TypeToken<ArrayList<Bicycle>>(){}.getType();
            this.bicycles = gson.fromJson(reader, listType);
            System.out.println("Дані успішно завантажено з файлу: " + fileName);
        } catch (IOException e) {
            System.err.println("Помилка при читанні файлу: " + e.getMessage());
        }
    }

}
