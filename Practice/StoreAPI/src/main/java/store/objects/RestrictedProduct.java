package store.objects;

public class RestrictedProduct extends Product
{

    private int minAge;
    private boolean requiresExciseStamp;
    private double maxAllowedSalesVolume;
    private boolean isHighValue;

    private final double EXCISE_MARK_PRICE = 8.50;

    public RestrictedProduct(int code, double price, String name, double discount, String productionDate,
                             int shelfLifeDays, double minStorageTemperature, double maxStorageTemperature,
                             int minAge, boolean requiresExciseStamp, double maxAllowedSalesVolume, boolean isHighValue)
    {
        super(code, price, name, discount, productionDate, shelfLifeDays, minStorageTemperature, maxStorageTemperature);
        this.minAge = minAge;
        this.requiresExciseStamp = requiresExciseStamp;
        this.maxAllowedSalesVolume = maxAllowedSalesVolume;
        this.isHighValue = isHighValue;
    }

    public RestrictedProduct(int code, double price, String name, int minAge) {
        super(code, price, name);
        this.minAge = minAge;
        this.requiresExciseStamp = (minAge >= 18);
        this.maxAllowedSalesVolume = 2.0;
        this.isHighValue = false;
    }

    public int getMinAge() { return minAge; }
    public boolean isRequiresExciseStamp() { return requiresExciseStamp; }
    public double getMaxAllowedSalesVolume() { return maxAllowedSalesVolume; }
    public boolean isHighValue() { return isHighValue; }

    public void setMinAge(int minAge) { if (minAge >= 0) this.minAge = minAge; }
    public void setRequiresExciseStamp(boolean requiresExciseStamp) { this.requiresExciseStamp = requiresExciseStamp; }
    public void setMaxAllowedSalesVolume(double maxAllowedSalesVolume) { if (maxAllowedSalesVolume > 0) this.maxAllowedSalesVolume = maxAllowedSalesVolume; }
    public void setHighValue(boolean highValue) { this.isHighValue = highValue; }

    @Override
    public double calculateFinalPrice(double taxRate)
    {
        double finalPrice = super.calculateFinalPrice(taxRate);

        if (this.requiresExciseStamp)
        {
            finalPrice += EXCISE_MARK_PRICE;
        }

        return finalPrice;
    }

    public double calculatePureStoreRevenue(double taxRate, double quantity) {
        if (quantity <= 0) return 0.0;
        double pureProductPrice = this.getPrice() * (1.0 - this.getDiscount()) * quantity;
        return pureProductPrice;
    }

    public int calculateMaxAllowedPackages(double singlePackageVolume) {
        if (singlePackageVolume <= 0 || this.maxAllowedSalesVolume <= 0) return 0;
        return (int) (this.maxAllowedSalesVolume / singlePackageVolume);
    }

    public boolean isCustomerAgeAllowed(int age)
    {
        if (this.minAge <= age)
            return true;
        return false;
    }

    @Override
    public String generateInvoiceRow(String currency) {
        String exciseLabel = this.requiresExciseStamp ? " [Акциз]" : "";
        String securityLabel = this.isHighValue ? " [Охорона]" : "";
        return super.generateInvoiceRow(currency) + " [Обмеження: " + this.minAge + "+]" + exciseLabel + securityLabel;
    }
}