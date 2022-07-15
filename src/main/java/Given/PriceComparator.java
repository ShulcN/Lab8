package Given;

import java.util.Comparator;

public class PriceComparator extends ComparatorClass {

    @Override
    public int compare(Product o1, Product o2) {
        return (int) (o1.getPrice()-o2.getPrice());
    }
}
