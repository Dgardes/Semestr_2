package org.example.drachlab_292;

public class Product {
    private String name;
    private int quantity;
    private double price;
    private int packNormal;
    private double total;

    public Product(String name, int quantity, double price, int packNormal) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.packNormal = packNormal;
        this.total = quantity * price;
    }

    public String getName() { return name; }
    public int getQuantity() { return quantity; }
    public double getPrice() { return price; }
    public int getPackNormal() { return packNormal; }
    public double getTotal() { return total; }
}