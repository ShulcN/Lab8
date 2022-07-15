package Given;

import java.util.Comparator;

public class IdComparator extends ComparatorClass {
    @Override
    public int compare(Product o1, Product o2) {
        return (int) (o1.getId() - o2.getId());
    }
}
