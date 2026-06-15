package Part_3;

public class Main {
    public static void main(String[] args) {
        Weapon oldSword = new Weapon("Rusty Sword", 15);
        Weapon newSword = new Weapon("Diamond Sword", 50);

        EquipmentSlot<Weapon> weaponSlot = new EquipmentSlot<>(oldSword);

        System.out.println(weaponSlot.getEquippedItem().getName());
        double buffedDamage = weaponSlot.calculateEffectiveValue(1.5);
        System.out.println(buffedDamage);

        boolean canUpgradeWeapon = weaponSlot.isUpgradeAvailable(newSword);
        System.out.println(canUpgradeWeapon);

        if (canUpgradeWeapon) {
            weaponSlot.equip(newSword);
        }
        System.out.println(weaponSlot.getEquippedItem().getName());


        Armor oldShield = new Armor("Wooden Shield", 5);
        Armor brokenShield = new Armor("Broken Plate", 2);

        EquipmentSlot<Armor> armorSlot = new EquipmentSlot<>(oldShield);

        System.out.println(armorSlot.getEquippedItem().getName());
        boolean canUpgradeArmor = armorSlot.isUpgradeAvailable(brokenShield);
        System.out.println(canUpgradeArmor);
    }
}