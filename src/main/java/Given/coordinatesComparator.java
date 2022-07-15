package Given;

import java.util.Comparator;

public class coordinatesComparator extends ComparatorClass{
    @Override
    public int compare(Product o1, Product o2) {
        int k=(int)Math.sqrt(Math.pow(o1.getCoordinates().getX(), 2)+Math.pow(o1.getCoordinates().getY(), 2));
        int l =(int)Math.sqrt(Math.pow(o2.getCoordinates().getX(), 2)+Math.pow(o2.getCoordinates().getY(), 2));
        return (k-l);
    }

}
