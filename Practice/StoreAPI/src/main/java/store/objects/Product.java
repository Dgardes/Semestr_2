package store.objects;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public abstract class Product implements Serializable
{
    private int code;
    private double price;
    private String name;
    private double discount;
    private String productionDate;
    private int shelfLifeDays;
    private double minStorageTemperature;
    private double maxStorageTemperature;

    private final double DEFAULT_DISCOUNT = 0.0;
    private final String DEFAULT_DATE = getTodayDate();
    private final int DEFAULT_SHELF_LIFE = 30;
    private final double DEFAULT_MIN_TEMP = 2.0;
    private final double DEFAULT_MAX_TEMP = 6.0;

    public Product(int code, double price, String name, double discount, String productionDate, int shelfLifeDays, double minStorageTemperature, double maxStorageTemperature) {
        this.code = code;
        this.price = price;
        this.name = name;
        this.discount = discount;
        this.productionDate = productionDate;
        this.shelfLifeDays = shelfLifeDays;
        this.minStorageTemperature = minStorageTemperature;
        this.maxStorageTemperature = maxStorageTemperature;
    }
    public Product(int code, double price, String name, double discount, String productionDate, int shelfLifeDays) {
        this.code = code;
        this.price = price;
        this.name = name;
        this.discount = discount;
        this.productionDate = productionDate;
        this.shelfLifeDays = shelfLifeDays;
        this.minStorageTemperature = DEFAULT_MIN_TEMP;
        this.maxStorageTemperature = DEFAULT_MAX_TEMP;
    }
    public Product(int code, double price, String name, double discount) {
        this.code = code;
        this.price = price;
        this.name = name;
        this.discount = discount;
        this.productionDate = DEFAULT_DATE;
        this.shelfLifeDays = DEFAULT_SHELF_LIFE;
        this.minStorageTemperature = DEFAULT_MIN_TEMP;
        this.maxStorageTemperature = DEFAULT_MAX_TEMP;
    }
    public Product(int code, double price, String name) {
        this.code = code;
        this.price = price;
        this.name = name;
        this.discount = DEFAULT_DISCOUNT;
        this.productionDate = DEFAULT_DATE;
        this.shelfLifeDays = DEFAULT_SHELF_LIFE;
        this.minStorageTemperature = DEFAULT_MIN_TEMP;
        this.maxStorageTemperature = DEFAULT_MAX_TEMP;
    }


    public int getCode() { return code; }
    public double getPrice() { return price; }
    public String getName() { return name; }
    public double getDiscount() { return discount; }
    public String getProductionDate() { return productionDate; }
    public int getShelfLifeDays() { return shelfLifeDays; }
    public double getMinStorageTemperature() { return minStorageTemperature; }
    public double getMaxStorageTemperature() { return maxStorageTemperature; }

    public void setCode(int code) { if(code > 0 ) this.code = code; }
    public void setPrice(double price) { if (price > 0) this.price = price; }
    public void setName(String name) { if (!name.isEmpty()) this.name = name; }
    public void setDiscount(double discount) { if (discount <= 1.0 && discount >= 0.0) this.discount = discount; }
    public void setShelfLifeDays(int shelfLifeDays) { if (shelfLifeDays >= 0) this.shelfLifeDays = shelfLifeDays; }
    public void setMinStorageTemperature(double minStorageTemperature) { this.minStorageTemperature = minStorageTemperature;}
    public void setMaxStorageTemperature(double maxStorageTemperature)
    {
        if (maxStorageTemperature >= this.minStorageTemperature) {
            this.maxStorageTemperature = maxStorageTemperature;
        }
    }

    public void setProductionDate(String productionDate)
    {
        if (productionDate == null || productionDate.isEmpty()) return;
        try
        {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
            LocalDate.parse(productionDate, formatter);
            this.productionDate = productionDate;
        }
        catch (Exception e) { return; }
    }

    public double calculateFinalPrice(double taxRate)
    {
        double priceWithDiscount = price * (1 - discount);
        return priceWithDiscount + (priceWithDiscount * taxRate);
    }

    public final void applyExtraDiscount(int extraDiscount)
    {
        double extraDiscountDouble = extraDiscount / 100.0;
        double newDiscount = this.discount + extraDiscountDouble;
        if (extraDiscount >= 0) {
            if (newDiscount >= 1.0) {
                newDiscount = 1.0;
            }
            this.discount = newDiscount;
        }
    }

    public final void applyExtraDiscount(double discountInMoney) {
        if (discountInMoney > 0 && this.price > 0) {
            double extraDiscountCoefficient = discountInMoney / this.price;
            double newDiscount = this.discount + extraDiscountCoefficient;
            if (newDiscount >= 1.0) {
                newDiscount = 1.0;
            }
            this.discount = newDiscount;
        }
    }

    public double calculateDiscountAmount(double taxRate) {
        if (this.discount <= 0.0) {
            return 0.0;
        }
        double fullPriceWithTax = this.price + (this.price * taxRate);
        double finalPriceWithTax = this.calculateFinalPrice(taxRate);

        double discountAmount = fullPriceWithTax - finalPriceWithTax;
        return discountAmount > 0.0 ? discountAmount : 0.0;
    }

    public String generateInvoiceRow(String currency) {
        String tempLabel = this.maxStorageTemperature <= 10.0 ? " [Зберігати при низькій температурі]" : "";
        String row =  "[Код: " + this.code + "] " + this.name + " " + String.format("%.2f", this.price) + " " + currency;
        if(this.discount > 0.0) row += " Знижка: " + String.format("%.2f", this.discount * 100) + "%";
        return  row + tempLabel;
    }

    private String getTodayDate()
    {
        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        return today.format(formatter);
    }

    public boolean isExpired()
    {
        try
        {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
            LocalDate prodDate = LocalDate.parse(this.productionDate, formatter);
            LocalDate expiryDate = prodDate.plusDays(this.shelfLifeDays);

            return LocalDate.now().isAfter(expiryDate);
        }
        catch (Exception e)
        {
            return true;
        }
    }
}