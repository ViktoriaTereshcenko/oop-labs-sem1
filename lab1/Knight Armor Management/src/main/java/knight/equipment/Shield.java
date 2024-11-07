package knight.equipment;

public class Shield extends Equipment {
    private double defense;

    public Shield(String name, double weight, double price, double defense) {
        super(name, weight, price);
        this.defense = defense;
    }

    public double getDefense() {
        return defense;
    }

    @Override
    public String toString() {
        return super.toString() + ", Захист: " + defense;
    }
}
