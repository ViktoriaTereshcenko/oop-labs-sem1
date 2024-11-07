package knight.equipment;

public class Weapon extends Equipment {
    private double damage;

    public Weapon(String name, double weight, double price, double damage) {
        super(name, weight, price);
        this.damage = damage;
    }

    public double getDamage() {
        return damage;
    }

    @Override
    public String toString() {
        return super.toString() + ", Шкода: " + damage;
    }
}
