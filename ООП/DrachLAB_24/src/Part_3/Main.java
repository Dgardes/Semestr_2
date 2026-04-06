package Part_3;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        BicycleCatalog catalog = new BicycleCatalog(new ArrayList<>());
        String filePath = "catalog_data.json";

        catalog.addBicycle(new Bicycle("Giant", "Roam 1", 28, 13.2, false, 24000));
        catalog.addBicycle(new Bicycle("Cube", "Aim Pro", 29, 14.7, false, 21500));
        catalog.addBicycle(new Bicycle("Specialized", "Turbo Vado", 28, 21.0, true, 115000));
        catalog.addBicycle(new Bicycle("Giant", "Talon 2", 29, 14.1, false, 26000));

        //збереэення
        catalog.saveToFile(filePath);

        //завантаження
        BicycleCatalog importedCatalog = new BicycleCatalog(new ArrayList<>());
        importedCatalog.loadFromFile(filePath);

        String searchBrand = "Giant";
        System.out.println("Шукаємо велосипеди бренду: " + searchBrand);

        ArrayList<Bicycle> foundByBrand = importedCatalog.findByBrand(searchBrand);
        for (Bicycle bicycle : foundByBrand) {
            System.out.println("Знайдено: " + bicycle);
        }

        if (!foundByBrand.isEmpty()) {
            int testID = foundByBrand.get(0).getID();
            System.out.println("Шукаємо велосипед за ID: " + testID);

            Bicycle foundByID = importedCatalog.findById(testID);
            if (foundByID != null) {
                System.out.println("Результат пошуку за ID: " + foundByID);
            } else {
                System.out.println("Велосипед з таким ID не знайдено.");
            }
        }
    }
}