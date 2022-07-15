package Given;

import java.util.Comparator;

public class ComparatorClass implements Comparator<Product> {
    @Override
    public Comparator<Product> reversed() {
        return Comparator.super.reversed();
    }
    @Override
    public int compare(Product o1, Product o2) {
        return (o1.compareTo(o2));
    }
}
