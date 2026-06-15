package Part_3;

public class EquipmentSlot<T extends Item> {
    private T equippedItem;

    public EquipmentSlot(T item) {
        this.equippedItem = item;
    }

    public double calculateEffectiveValue(double multiplier) {
        if (equippedItem == null) {
            return 0;
        }
        return equippedItem.getBaseValue() * multiplier;
    }

    public boolean isUpgradeAvailable(T newItem) {
        if (equippedItem == null) {
            return newItem != null;
        }
        return newItem.getBaseValue() > equippedItem.getBaseValue();
    }

    public T getEquippedItem() {
        return equippedItem;
    }

    public void equip(T newItem) {
        this.equippedItem = newItem;
    }
}