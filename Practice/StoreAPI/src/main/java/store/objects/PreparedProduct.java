package store.objects;

import java.time.LocalTime;

public class PreparedProduct extends Product {
    private String chefName;
    private String preparationDepartment;
    private boolean isHot;
    private int cookingTimeMinutes;

    private final String DEFAULT_CHEF = "Шеф-кухар магазину";
    private final String DEFAULT_DEPARTMENT = "Кулінарія";

    public PreparedProduct(int code, double price, String name, double discount, String productionDate,
                           int shelfLifeDays, double minStorageTemperature, double maxStorageTemperature,
                           String chefName, String preparationDepartment, boolean isHot, int cookingTimeMinutes) {
        super(code, price, name, discount, productionDate, shelfLifeDays, minStorageTemperature, maxStorageTemperature);
        this.chefName = chefName;
        this.preparationDepartment = preparationDepartment;
        this.isHot = isHot;
        this.cookingTimeMinutes = cookingTimeMinutes;
    }

    public PreparedProduct(int code, double price, String name) {
        super(code, price, name);
        this.chefName = DEFAULT_CHEF;
        this.preparationDepartment = DEFAULT_DEPARTMENT;
        this.isHot = false;
        this.cookingTimeMinutes = 15;
    }

    public String getChefName() {
        return chefName;
    }

    public String getPreparationDepartment() {
        return preparationDepartment;
    }

    public boolean isHot() {
        return isHot;
    }

    public int getCookingTimeMinutes() {
        return cookingTimeMinutes;
    }

    public void setChefName(String chefName) {
        if (chefName != null && !chefName.trim().isEmpty()) this.chefName = chefName;
    }

    public void setHot(boolean hot) {
        this.isHot = hot;
    }

    public void setCookingTimeMinutes(int cookingTimeMinutes) {
        if (cookingTimeMinutes > 0) this.cookingTimeMinutes = cookingTimeMinutes;
    }

    public void setPreparationDepartment(String preparationDepartment) {
        if (preparationDepartment != null && !preparationDepartment.trim().isEmpty()) {
            this.preparationDepartment = preparationDepartment;
        }
    }

    public double calculateEveningMarkdown(double basePrice) {
        int currentHour = java.time.LocalTime.now().getHour();
        if (currentHour >= 19 && currentHour <= 23) {
            double eveningDiscount = 0.30;
            this.setDiscount(eveningDiscount);
            return basePrice * (1.0 - eveningDiscount);
        }
        return basePrice;
    }

    @Override
    public double calculateFinalPrice(double taxRate) {
        double standardPrice = super.calculateFinalPrice(taxRate);
        return calculateEveningMarkdown(standardPrice);
    }

    public int calculatePortionsPerHour(int chefsCount) {
        if (chefsCount <= 0 || this.cookingTimeMinutes <= 0) return 0;

        double portionsPerChef = 60.0 / this.cookingTimeMinutes;
        double totalPortions = portionsPerChef * chefsCount;

        if (this.isHot) {
            totalPortions = totalPortions * 1.2;
        }

        return (int) totalPortions;
    }

    public int calculateCookingTimeForOrder(int portionsCount, int chefsCount) {
        if (portionsCount <= 0 || chefsCount <= 0) return 0;
        int portionsPerChef = (int) Math.ceil((double) portionsCount / chefsCount);
        return portionsPerChef * this.cookingTimeMinutes;
    }

    @Override
    public String generateInvoiceRow(String currency) {
        String temperatureLabel = this.isHot ? " [Гаряча викладка]" : " [Холодна викладка]";
        return super.generateInvoiceRow(currency) + temperatureLabel +
                " [Цех: " + this.preparationDepartment + ", Кухар: " + this.chefName + ", Техкарта: " + this.cookingTimeMinutes + " хв]";
    }
}