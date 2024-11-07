package main;

import knight.Knight;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        Knight knight = new Knight();

        try {
            knight.loadEquipmentFromFile("src/equipment.csv");

            System.out.println("Екіпірування лицаря:");
            knight.displayEquipment();

            System.out.println("Загальна вартість екіпірування: " + knight.calculateTotalPrice());

            System.out.println("\nЕкіпірування після сортування за вагою:");
            knight.sortEquipmentByWeight();
            knight.displayEquipment();

            System.out.println("\nЕлементи амуніції в діапазоні цін 100-200:");
            knight.findEquipmentByPriceRange(100, 200).forEach(System.out::println);

        } catch (IOException e) {
            System.err.println("Помилка під час завантаження файлу: " + e.getMessage());
        }
    }
}
