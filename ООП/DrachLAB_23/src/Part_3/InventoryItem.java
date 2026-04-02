package Part_3;

import java.io.Serializable;

public class InventoryItem implements Serializable
{
    private String inventoryNum;
    private String name;
    private int count;
    private String description;

    public InventoryItem(String inventoryNum, String name, int count, String description) {
        this.inventoryNum = inventoryNum;
        this.name = name;
        this.count = count;
        this.description = description;
    }

    public InventoryItem(String inventoryNum, String name, int count) {
        this.inventoryNum = inventoryNum;
        this.name = name;
        this.count = count;
        this.description = "опис відсутній";
    }

    public InventoryItem() {
    }

    public String getInventoryNum() {
        return inventoryNum;
    }

    public void setInventoryNum(String inventoryNum) {
        this.inventoryNum = inventoryNum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "InventoryItem{" +
                "inventoryNum='" + inventoryNum + '\'' +
                ", name='" + name + '\'' +
                ", count=" + count +
                ", description='" + description + '\'' +
                '}';
    }
}
