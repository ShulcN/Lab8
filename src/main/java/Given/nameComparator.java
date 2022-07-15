package Given;

import java.util.Comparator;

public class nameComparator extends ComparatorClass {
    @Override
    public int compare(Product o1, Product o2) {
        return (int) (o1.getName().compareTo(o2.getName()));
    }
}
