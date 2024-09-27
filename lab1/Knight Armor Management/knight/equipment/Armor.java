package knight.equipment;

public class Armor extends Equipment {
    private String material;

    public Armor(String name, double weight, double price, String material) {
        super(name, weight, price);
        this.material = material;
    }

    public String getMaterial() {
        return material;
    }

    @Override
    public String toString() {
        return super.toString() + ", Матеріал: " + material;
    }
}
