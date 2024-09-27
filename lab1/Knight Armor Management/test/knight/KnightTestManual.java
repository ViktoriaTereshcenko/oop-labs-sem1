package knight;

import knight.equipment.Armor;
import knight.equipment.Weapon;
import knight.equipment.Shield;

public class KnightTestManual {

    public static void main(String[] args) {
        testCalculateTotalPrice();
        testFindEquipmentByPriceRange();
        testSortEquipmentByWeight();
    }

    public static void testCalculateTotalPrice() {
        Knight knight = new Knight();
        knight.equip(new Armor("Латні обладунки", 15.0, 300.0, "Сталь"));
        knight.equip(new Weapon("Меч", 3.0, 150.0, 50.0));

        double totalPrice = knight.calculateTotalPrice();
        if (Math.abs(totalPrice - 450.0) < 0.01) {
            System.out.println("testCalculateTotalPrice: Passed");
        } else {
            System.out.println("testCalculateTotalPrice: Failed. Expected 450.0, but got " + totalPrice);
        }
    }

    public static void testFindEquipmentByPriceRange() {
        Knight knight = new Knight();
        knight.equip(new Armor("Латні обладунки", 15.0, 300.0, "Сталь"));
        knight.equip(new Weapon("Меч", 3.0, 150.0, 50.0));

        int resultCount = knight.findEquipmentByPriceRange(100, 200).size();
        if (resultCount == 1) {
            System.out.println("testFindEquipmentByPriceRange: Passed");
        } else {
            System.out.println("testFindEquipmentByPriceRange: Failed. Expected 1, but got " + resultCount);
        }
    }

    public static void testSortEquipmentByWeight() {
        Knight knight = new Knight();
        knight.equip(new Armor("Латні обладунки", 15.0, 300.0, "Сталь"));
        knight.equip(new Weapon("Меч", 3.0, 150.0, 50.0));
        knight.equip(new Shield("Круглий щит", 5.0, 100.0, 30.0));

        knight.sortEquipmentByWeight();

        boolean isSorted = knight.getEquipment().get(0).getWeight() <= knight.getEquipment().get(1).getWeight() &&
                           knight.getEquipment().get(1).getWeight() <= knight.getEquipment().get(2).getWeight();

        if (isSorted) {
            System.out.println("testSortEquipmentByWeight: Passed");
        } else {
            System.out.println("testSortEquipmentByWeight: Failed");
        }
    }
}
