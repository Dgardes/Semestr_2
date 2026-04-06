package Part_2;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

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

}
