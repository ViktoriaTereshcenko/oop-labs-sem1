package knight.loader;

import knight.equipment.Armor;
import knight.equipment.Equipment;
import knight.equipment.Shield;
import knight.equipment.Weapon;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class EquipmentLoader {

    public static List<Equipment> loadEquipmentFromFile(String filePath) throws IOException {
        List<Equipment> equipmentList = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line;

        reader.readLine();

        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(",");

            String type = parts[0];
            String name = parts[1];
            double weight = Double.parseDouble(parts[2]);
            double price = Double.parseDouble(parts[3]);
            String extra = parts[4];

            switch (type) {
                case "Armor":
                    equipmentList.add(new Armor(name, weight, price, extra));
                    break;
                case "Weapon":
                    double damage = Double.parseDouble(extra);
                    equipmentList.add(new Weapon(name, weight, price, damage));
                    break;
                case "Shield":
                    double defense = Double.parseDouble(extra);
                    equipmentList.add(new Shield(name, weight, price, defense));
                    break;
                default:
                    System.err.println("Невідомий тип амуніції: " + type);
                    break;
            }
        }
        reader.close();
        return equipmentList;
    }
}
