package store.objects;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class PieceProduct extends Product
{
    private int shelfLifeAfterOpeningDays;
    private double packageWeightGrams;
    private String packagingMaterial;
    private boolean isFragile;

    private final double DEFAULT_PKG_WEIGHT = 15.0;
    private final String DEFAULT_MATERIAL = "Поліетилен";
    private final int DEFAULT_OPENED_SHELF_LIFE = 3;

    public PieceProduct(int code, double price, String name, double discount, String productionDate,
                        int shelfLifeDays, double minStorageTemperature, double maxStorageTemperature,
                        double packageWeightGrams, String packagingMaterial, boolean isFragile, int shelfLifeAfterOpeningDays)
    {
        super(code, price, name, discount, productionDate, shelfLifeDays, minStorageTemperature, maxStorageTemperature);
        this.packageWeightGrams = packageWeightGrams;
        this.packagingMaterial = packagingMaterial;
        this.isFragile = isFragile;
        this.shelfLifeAfterOpeningDays = shelfLifeAfterOpeningDays;
    }

    public PieceProduct(int code, double price, String name) {
        super(code, price, name);
        this.packageWeightGrams = DEFAULT_PKG_WEIGHT;
        this.packagingMaterial = DEFAULT_MATERIAL;
        this.isFragile = false;
        this.shelfLifeAfterOpeningDays = DEFAULT_OPENED_SHELF_LIFE;
    }

    public double getPackageWeightGrams() { return packageWeightGrams; }
    public String getPackagingMaterial() { return packagingMaterial; }
    public boolean isFragile() { return isFragile; }
    public int getShelfLifeAfterOpeningDays() { return shelfLifeAfterOpeningDays; }

    public void setPackageWeightGrams(double packageWeightGrams) { if (packageWeightGrams >= 0) this.packageWeightGrams = packageWeightGrams; }
    public void setPackagingMaterial(String packagingMaterial) { if (!packagingMaterial.isEmpty()) this.packagingMaterial = packagingMaterial; }
    public void setFragile(boolean fragile) { this.isFragile = fragile; }
    public void setShelfLifeAfterOpeningDays(int shelfLifeAfterOpeningDays) { if (shelfLifeAfterOpeningDays >= 0) this.shelfLifeAfterOpeningDays = shelfLifeAfterOpeningDays; }

    public boolean spoilsQuicklyAfterOpening(int criticalDays) {
        if (criticalDays <= 0) return false;
        return this.shelfLifeAfterOpeningDays < criticalDays;
    }

    public boolean requiresEcoFriendlyDisposal() {
        return "пластик".equals(this.packagingMaterial.toLowerCase()) || "тетрапак".equals(this.packagingMaterial.toLowerCase());
    }

    public String getNewShelfLifeAfterOpening() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate currentDate = LocalDate.now();
        try
        {
            LocalDate prodDate = LocalDate.parse(this.getProductionDate(), formatter);
            LocalDate finalExpiryDate = prodDate.plusDays(this.getShelfLifeDays());
            LocalDate openedExpiryDate = currentDate.plusDays(this.shelfLifeAfterOpeningDays);
            LocalDate realExpiryDate = finalExpiryDate.isBefore(openedExpiryDate) ? finalExpiryDate : openedExpiryDate;
            return realExpiryDate.toString();
        }
        catch (Exception ex)
        {
            return null;
        }
    }

    @Override
    public String generateInvoiceRow(String currency) {
        String fragileLabel = this.isFragile ? " [Крихке]" : "";
        return super.generateInvoiceRow(currency) + fragileLabel + " [Штучний, Матеріал: " + this.packagingMaterial + "]";
    }

}