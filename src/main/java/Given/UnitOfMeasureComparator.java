package Given;

import java.util.Comparator;

public class UnitOfMeasureComparator extends ComparatorClass {

    @Override
    public int compare(Product o1, Product o2) {
        return (o1.getUnitOfMeasure().toString().compareTo(o2.getUnitOfMeasure().toString()));
    }
}
