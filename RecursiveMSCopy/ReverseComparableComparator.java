import java.util.Comparator;

/**
 * Returns reverse of natural order
 * 
 * @author mvail
 *
 * @param <T> a Comparable type
 */
public class ReverseComparableComparator<T extends Comparable<T>> implements Comparator<T> {

	@Override
	public int compare(T o1, T o2) {
		return (o1.compareTo(o2));
	}

}

