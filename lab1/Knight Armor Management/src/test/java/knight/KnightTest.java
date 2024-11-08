package knight;

import knight.equipment.Armor;
import knight.equipment.Weapon;
import knight.equipment.Shield;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class KnightTest {
    private Knight knight;

    @Before
    public void setUp() {
        knight = new Knight();
        knight.equip(new Armor("Латні обладунки", 15.0, 300.0, "Сталь"));
        knight.equip(new Weapon("Меч", 3.0, 150.0, 50.0));
        knight.equip(new Shield("Круглий щит", 5.0, 100.0, 30.0));
    }

    @Test
    public void testCalculateTotalPrice() {
        double totalPrice = knight.calculateTotalPrice();
        assertEquals(550.0, totalPrice, 0.01, "Загальна вартість екіпірування має бути 550.0");
    }

    @Test
    public void testFindEquipmentByPriceRange() {
        int resultCount = knight.findEquipmentByPriceRange(100, 200).size();
        assertEquals(2, resultCount, "Має бути 2 елементи амуніції в діапазоні цін 100-200.");
    }

    @Test
    public void testSortEquipmentByWeight() {
        knight.sortEquipmentByWeight();
        assertTrue(knight.getEquipment().get(0).getWeight() <= knight.getEquipment().get(1).getWeight());
        assertTrue(knight.getEquipment().get(1).getWeight() <= knight.getEquipment().get(2).getWeight());
    }
}
