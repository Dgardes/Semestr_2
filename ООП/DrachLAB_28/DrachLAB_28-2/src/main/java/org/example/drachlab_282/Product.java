package org.example.drachlab_282;

public class Product {
    private String name;
    private String price;
    private String category;
    private String status;

    public Product(String name, String price, String category, String status) {
        this.name = name;
        this.price = price;
        this.category = category;
        this.status = status;
    }

    public String getName() { return name; }
    public String getPrice() { return price; }
    public String getCategory() { return category; }
    public String getStatus() { return status; }

    @Override
    public String toString() {
        return name;
    }
}