package knight.equipment;

public abstract class Equipment {
    private String name;
    private double weight;
    private double price;

    public Equipment(String name, double weight, double price) {
        this.name = name;
        this.weight = weight;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getWeight() {
        return weight;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return name + " (Вага: " + weight + ", Ціна: " + price + ")";
    }
}
