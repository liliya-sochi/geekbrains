import java.util.ArrayList;

public class Box<T extends Fruit> {
    private ArrayList<T> fruitBox = new ArrayList<>();

    public void addFruit(T fruit) {
        fruitBox.add(fruit);
    }

    public float getWeight() {
        float weight = 0;
        for (T fruit : fruitBox) {
            if (fruit instanceof Apple) weight += 1.0f;
            if (fruit instanceof Orange) weight += 1.5f;
        }
        System.out.println("Вес коробки: " + weight);
        return weight;
    }

    public boolean compare(Box<?> another) {
        if (this.getWeight() == another.getWeight()) {
            System.out.println("Коробки равны!");
            return true;
        } else {
            System.out.println("Коробки не равны!");
            return false;
        }
    }

    public void unite(Box<T> another) {
        for (T fruit : fruitBox) {
            another.addFruit(fruit);
        }
        fruitBox.clear();
    }
}
