package Given;

import java.util.Comparator;

public class OwnerComparator extends ComparatorClass{

    @Override
    public int compare(Product o1, Product o2) {
        return (int) (o1.getLogin().compareTo(o2.getLogin()));
    }
}
