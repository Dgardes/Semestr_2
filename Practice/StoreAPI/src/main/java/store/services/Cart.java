package store.services;

import store.objects.Product;
import store.objects.WeightProduct;

import java.util.HashMap;
import java.util.Map;

public class Cart
{
    private Map<Product, Double> items;

    public Cart() {
        this.items = null;
    }

    public void addProduct(Product product, double quantity)
    {
        if(product == null) return;
        if(items == null) items = new HashMap<Product, Double>();
        double finalQuantity = quantity;
        if(items.containsKey(product)) finalQuantity += items.get(product);
        items.put(product, finalQuantity);
    }

    public void removeProduct(Product product, double quantity)
    {
        if(product == null) return;
        if(items == null) return;
        if(!items.containsKey(product)) return;

        double productQuantity = items.get(product);
        if(productQuantity - quantity <= 0) items.remove(product);
        else items.put(product, productQuantity - quantity);
    }

    public void clear()
    {
        items = null;
    }

    public Map<Product, Double> getItems() {
        return this.items;
    }

    public double calculateTotalCartCost(double taxRate)
    {
        double total = 0;
        for(Map.Entry<Product, Double> entry : items.entrySet())
        {
            Product product = entry.getKey();
            Double quantity = entry.getValue();

            if(product == null) continue;
            if(product instanceof WeightProduct)
            {
                total += ((WeightProduct) product).calculateFinalPrice(taxRate, quantity);
            }
            else
            {
                total += product.calculateFinalPrice(taxRate) * quantity;
            }
        }
        return total;
    }
}