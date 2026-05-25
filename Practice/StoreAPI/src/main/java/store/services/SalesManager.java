package store.services;

import store.objects.Product;
import store.objects.RestrictedProduct;
import store.objects.WeightProduct;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;

public class SalesManager
{
    private Cart currentCart;
    private FileManager fileManager;
    private LogBuilder logBuilder;
    private Map<Product, Double> catalog;

    public SalesManager(FileManager fileManager, LogBuilder logBuilder) {
        this.currentCart = new Cart();
        this.fileManager = fileManager;
        this.logBuilder = logBuilder;
        this.catalog = null;
    }


    public boolean scanAndAddProduct(int productCode, double quantity, int customerAge) {
        if (quantity <= 0) return false;
        if (catalog == null) return false;

        Product foundProduct = null;

        for (Map.Entry<Product, Double> entry : catalog.entrySet())
        {
            Product p = entry.getKey();
            if (p != null && p.getCode() == productCode)
            {
                foundProduct = p;
                break;
            }
        }
        if (foundProduct == null) return false;
        if (foundProduct instanceof RestrictedProduct) {
            if (!((RestrictedProduct) foundProduct).isCustomerAgeAllowed(customerAge)) return false;
        }

        currentCart.addProduct(foundProduct, quantity);
        return true;
    }

    public void removeProductFromCart(Product product, double quantity)
    {
        currentCart.removeProduct(product, quantity);
    }

    public void cancelCurrentSale() {
        currentCart.clear();
    }

    public void calculateTotalCartCost(double taxRate)
    {
        currentCart.calculateTotalCartCost(taxRate);
    }

    public void checkout(double taxRate, String currentDate) {
        if (currentCart.getItems() == null || currentCart.getItems().isEmpty()) return;
        Map<Product, double[]> dailySales = fileManager.loadSoldProducts(currentDate);

        for (Map.Entry<Product, Double> entry : currentCart.getItems().entrySet())
        {
            Product product = entry.getKey();
            Double quantityInCart = entry.getValue();

            if (catalog.containsKey(product))
            {
                double currentStock = catalog.get(product);
                currentStock -= quantityInCart;
                if (currentStock < 0) currentStock = 0;
                catalog.put(product, currentStock);
            }

            double itemFinalPrice;
            if (product instanceof WeightProduct)
            {
                itemFinalPrice = ((WeightProduct) product).calculateFinalPrice(taxRate, quantityInCart) / quantityInCart;
            }
            else
            {
                itemFinalPrice = product.calculateFinalPrice(taxRate);
            }
            double exactItemCost = itemFinalPrice * quantityInCart;

            if (dailySales.containsKey(product))
            {
                double[] data = dailySales.get(product);
                data[0] += quantityInCart;
                data[1] += exactItemCost;
            }
            else
            {
                double[] newData = new double[2];
                newData[0] = quantityInCart;
                newData[1] = exactItemCost;
                dailySales.put(product, newData);
            }
        }

        double totalCost = currentCart.calculateTotalCartCost(taxRate);
        int nextReceiptNumber = fileManager.getRecieptsCount(currentDate) + 1;
        String receiptText = LogBuilder.generateCustomerReceipt(
                currentCart.getItems(), taxRate, totalCost, nextReceiptNumber, currentDate
        );
        fileManager.writeReceiptFile(currentDate, receiptText);

        fileManager.saveCatalog(this.catalog);
        fileManager.saveSoldProducts(currentDate, dailySales);
        currentCart.clear();
    }

    public void loadStoreData() {
        this.catalog = fileManager.loadCatalog();
    }

    public void saveStoreData() {
        if (this.catalog != null)
        {
            fileManager.saveCatalog(this.catalog);
        }
    }

    public boolean createNewProductInCatalog(Product product, double initialStock) {
        if (product == null || initialStock < 0) return false;
        if (this.catalog == null)
        {
            this.catalog = new java.util.HashMap<Product, Double>();
        }

        int maxCode = 0;
        for (Map.Entry<Product, Double> entry : catalog.entrySet())
        {
            Product p = entry.getKey();
            if (p != null && p.getCode() > maxCode)
            {
                maxCode = p.getCode();
            }
        }
        product.setCode(maxCode + 1);
        catalog.put(product, initialStock);
        saveStoreData();
        return true;
    }

    public boolean replenishProductStock(Product newProduct, double quantity) {
        if (newProduct == null || quantity <= 0 || this.catalog == null) return false;

        if (newProduct.getCode() == 0)
        {
            int maxCode = 0;
            for (Map.Entry<Product, Double> entry : catalog.entrySet()) {
                Product p = entry.getKey();
                if (p != null && p.getCode() > maxCode) {
                    maxCode = p.getCode();
                }
            }
            newProduct.setCode(maxCode + 1);
            catalog.put(newProduct, quantity);
            saveStoreData();
            return true;
        }

        Product existingProduct = null;
        double currentStock = 0.0;

        for (Map.Entry<Product, Double> entry : catalog.entrySet())
        {
            Product p = entry.getKey();
            if (p != null && p.getCode() == newProduct.getCode())
            {
                existingProduct = p;
                currentStock = entry.getValue();
                break;
            }
        }

        if (existingProduct == null)
        {
            catalog.put(newProduct, quantity);
            saveStoreData();
            return true;
        }

        if (currentStock == 0.0)
        {
            catalog.remove(existingProduct);
            catalog.put(newProduct, quantity);
            saveStoreData();
            return true;
        } else
        {
            return false;
        }
    }

    public void removeProductFromCatalog(int productCode) {
        if (this.catalog == null || this.catalog.isEmpty()) return;

        Product productToRemove = null;
        for (Map.Entry<Product, Double> entry : catalog.entrySet())
        {
            Product p = entry.getKey();
            if (p != null && p.getCode() == productCode)
            {
                productToRemove = p;
                break;
            }
        }

        if (productToRemove != null)
        {
            catalog.remove(productToRemove);
            saveStoreData();
        }
    }

    public void performWasteInspection(String currentDate) {
        if (this.catalog == null || this.catalog.isEmpty()) return;

        Map<Product, Double> expiredProducts = new java.util.HashMap<Product, Double>();

        for (Map.Entry<Product, Double> entry : catalog.entrySet())
        {
            Product product = entry.getKey();
            double stock = entry.getValue();
            if (product != null && product.isExpired() && stock > 0) {
                expiredProducts.put(product, stock);
            }
        }
        if (expiredProducts.isEmpty()) return;

        for (Product p : expiredProducts.keySet()) {
            catalog.put(p, 0.0);
        }
        String wasteReportText = LogBuilder.generateWasteReport(expiredProducts, currentDate);
        fileManager.writeWasteReportFile(currentDate, wasteReportText);
        saveStoreData();
    }

    public void closeDailyShift(String currentDate) {
        if (this.catalog == null) return;
        Map<Product, double[]> dailySales = fileManager.loadSoldProducts(currentDate);
        String summaryReportText = LogBuilder.generateDailySalesSummaryReport(dailySales, currentDate);
        fileManager.writeDailySummaryReport(currentDate, summaryReportText);
    }

    public Cart getCurrentCart() {
        return currentCart;
    }

    public Map<Product, Double> getCatalog() {
        return catalog;
    }
}