package Given;

import java.util.Comparator;

public class dataComparator extends ComparatorClass {

    @Override
    public int compare(Product o1, Product o2) {
        return (int) (o1.getCreationDate().compareTo(o2.getCreationDate()));
    }
}
