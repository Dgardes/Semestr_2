package store.objects;

public class WeightProduct extends Product
{
    private double weightKg;
    private String productGrade;
    private boolean isOrganic;
    private boolean isSoft;

    private final String DEFAULT_GRADE = "Перший сорт";
    private final double WHOLESALE_DISCOUNT = 0.1;

    public WeightProduct(int code, double price, String name, double discount, String productionDate,
                         int shelfLifeDays, double minStorageTemperature, double maxStorageTemperature,
                         double weightKg, String productGrade, boolean isOrganic, boolean isSoft)
    {
        super(code, price, name, discount, productionDate, shelfLifeDays, minStorageTemperature, maxStorageTemperature);
        this.weightKg = weightKg;
        this.productGrade = productGrade;
        this.isOrganic = isOrganic;
        this.isSoft = isSoft;
    }

    public WeightProduct(int code, double price, String name, double weightKg) {
        super(code, price, name);
        this.weightKg = weightKg;
        this.productGrade = "Перший сорт";
        this.isOrganic = true;
        this.isSoft = false;
    }

    public double getWeightKg() { return weightKg; }
    public String getProductGrade() { return productGrade; }
    public boolean isSoft() { return isSoft; }
    public boolean isOrganic() { return isOrganic; }

    public void setWeightKg(double weightKg) { if (weightKg > 0.0) this.weightKg = weightKg; }
    public void setOrganic(boolean organic) { isOrganic = organic; }
    public void setSoft(boolean soft) { isSoft = soft; }
    public void setProductGrade(String productGrade)
    {
        if (productGrade != null && !productGrade.trim().isEmpty())
            this.productGrade = productGrade;
    }

    public double calculateCostByWeight(double actualWeightKg) {
        if (this.weightKg <= 0 || actualWeightKg <= 0) return 0.0;
        return getPrice() * (actualWeightKg / this.weightKg);
    }

    public double calculateOrganicPremium(double basePrice) {
        if (!this.isOrganic) return 0.0;
        double organicPremiumRate = 0.15;
        return basePrice * organicPremiumRate;
    }

    public double calculateFinalPrice(double taxRate, double actualWeightKg) {
        if (actualWeightKg <= 0) return 0.0;

        double pureCost = calculateCostByWeight(actualWeightKg);
        double costWithDiscount = pureCost * (1.0 - getDiscount());
        double costWithTax = costWithDiscount + (costWithDiscount * taxRate);
        costWithTax += calculateOrganicPremium(costWithTax);

        if (actualWeightKg >= 5.0) {
            costWithTax = costWithTax * (1.0 - WHOLESALE_DISCOUNT);
        }

        if (this.isSoft) {
            double protectiveBoxPrice = 12.50;
            costWithTax += protectiveBoxPrice;
        }

        return costWithTax;
    }

    @Override
    public String generateInvoiceRow(String currency) {
        String organicLabel = this.isOrganic ? " [ЕКО-Продукт]" : "";
        String softLabel = this.isSoft ? " [М'яке]" : "";
        return super.generateInvoiceRow(currency) + " [Ваговий, Сорт: " + this.productGrade + "]" + softLabel + organicLabel;
    }
}
