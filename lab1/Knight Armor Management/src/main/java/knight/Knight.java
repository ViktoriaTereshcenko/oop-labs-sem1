package knight;

import knight.equipment.Equipment;
import knight.loader.EquipmentLoader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Knight {
    private List<Equipment> equipmentList = new ArrayList<>();

    public void equip(Equipment equipment) {
        equipmentList.add(equipment);
    }

    public void loadEquipmentFromFile(String filePath) throws IOException {
        equipmentList = EquipmentLoader.loadEquipmentFromFile(filePath);
    }

    public double calculateTotalPrice() {
        return equipmentList.stream().mapToDouble(Equipment::getPrice).sum();
    }

    public void sortEquipmentByWeight() {
        equipmentList.sort(Comparator.comparingDouble(Equipment::getWeight));
    }

    public List<Equipment> findEquipmentByPriceRange(double minPrice, double maxPrice) {
        return equipmentList.stream()
            .filter(e -> e.getPrice() >= minPrice && e.getPrice() <= maxPrice)
            .collect(Collectors.toList());
    }

    public void displayEquipment() {
        equipmentList.forEach(System.out::println);
    }

    public List<Equipment> getEquipment() {
        return equipmentList;
    }
}
