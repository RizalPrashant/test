import java.util.Comparator;
import java.util.ListIterator;

/**
 * Class for sorting lists that implement the IUListWithListIterator interface,
 * using ordering defined by class of objects in list or a Comparator. As
 * written uses Quicksort algorithm.
 *
 * @author CS221
 */
public class Sort {
	/**
	 * Returns a new list that implements the IUListWithListIterator interface.
	 * As configured, uses WrappedDLL. Must be changed if using your own
	 * IUDoubleLinkedList class.
	 * 
	 * @return a new list that implements the IUListWithListIterator interface
	 */
	private static <T> IUListWithListIterator<T> newList() {
		return new WrappedDLL<T>(); // TODO: replace with your
									// IUDoubleLinkedList for extra-credit
	}

	/**
	 * Sorts a list that implements the IUListWithListIterator interface using
	 * compareTo() method defined by class of objects in list. DO NOT MODIFY
	 * THIS METHOD
	 * 
	 * @param <T>
	 *            The class of elements in the list, must extend Comparable
	 * @param list
	 *            The list to be sorted, implements IUListWithListIterator
	 *            interface
	 * @see IUListWithListIterator
	 */
	public static <T extends Comparable<T>> void sort(IUListWithListIterator<T> list) {
		quicksort(list);
	}

	/**
	 * Sorts a list that implements the IUListWithListIterator interface using
	 * given Comparator. DO NOT MODIFY THIS METHOD
	 * 
	 * @param <T>
	 *            The class of elements in the list
	 * @param list
	 *            The list to be sorted, implements IUListWithListIterator
	 *            interface
	 * @param c
	 *            The Comparator used
	 * @see IUListWithListIterator
	 */
	public static <T> void sort(IUListWithListIterator<T> list, Comparator<T> c) {
		quicksort(list, c);
	}

	/**
	 * Quicksort algorithm to sort objects in a list that implements the
	 * IUListWithListIterator interface, using compareTo() method defined by
	 * class of objects in list. DO NOT MODIFY THIS METHOD SIGNATURE
	 * 
	 * @param <T>
	 *            The class of elements in the list, must extend Comparable
	 * @param list
	 *            The list to be sorted, implements IUListWithListIterator
	 *            interface
	 */
	private static <T extends Comparable<T>> void quicksort(IUListWithListIterator<T> list) {
		if (list.size() < 2) {
			return;
		}
		IUListWithListIterator<T> leftList = newList();
		IUListWithListIterator<T> rightList = newList();
		T pivot = list.last();

		while (list.size() != 0) {

			if (list.first().compareTo(pivot) < 0) {
				leftList.add(list.removeFirst());
			} else if (list.first().compareTo(pivot) > 0) {
				rightList.add(list.removeFirst());
			} else if (list.first().compareTo(pivot) == 0) {
				if (!rightList.contains(pivot)) {
					rightList.addToFront(list.removeFirst());
				} else {

					leftList.addToFront(list.removeFirst());
				}

			}

			// if(list.first().compareTo( list.last()) <= 0){
			// T temp = list.first();
			// leftList.add(temp);
			// }
			// if(list.first().compareTo(list.last()) > 0){
			// T temp = list.last();
			// rightList.add(temp);
			// }
			// } while (list.first().compareTo( list.last()) <= 0);

		}

		quicksort(leftList);
		quicksort(rightList);

		while (!rightList.isEmpty()) {
			list.addToFront(rightList.removeLast());
		}

		while (!leftList.isEmpty()) {
			list.addToFront(leftList.removeLast());
		}

		// TODO: Implement recursive quicksort algorithm
	}

	/**
	 * Quicksort algorithm to sort objects in a list that implements the
	 * IUListWithListIterator interface, using the given Comparator. DO NOT
	 * MODIFY THIS METHOD SIGNATURE
	 * 
	 * @param <T>
	 *            The class of elements in the list
	 * @param list
	 *            The list to be sorted, implements IUListWithListIterator
	 *            interface
	 * @param c
	 *            The Comparator used
	 */
	private static <T> void quicksort(IUListWithListIterator<T> list, Comparator<T> c) {
		// TODO: Implement recursive quicksort algorithm using Comparator
		if (list.size() < 2) {
			return;
		}
		IUListWithListIterator<T> leftList = newList();
		IUListWithListIterator<T> rightList = newList();
		T pivot = list.last();

		while (list.size() != 0) {

			if (c.compare(list.first(), pivot) < 0) {
				leftList.add(list.removeFirst());
			} else if (c.compare(list.first(), pivot) > 0) {
				rightList.add(list.removeFirst());
			} else if (c.compare(list.first(), pivot) == 0) {
				if (!rightList.contains(pivot)) {
					rightList.addToFront(list.removeFirst());
				} else {

					leftList.addToFront(list.removeFirst());
				}

			}

			// if(list.first().compareTo( list.last()) <= 0){
			// T temp = list.first();
			// leftList.add(temp);
			// }
			// if(list.first().compareTo(list.last()) > 0){
			// T temp = list.last();
			// rightList.add(temp);
			// }
			// } while (list.first().compareTo( list.last()) <= 0);

		}

		quicksort(leftList, c);
		quicksort(rightList, c);

		while (!rightList.isEmpty()) {
			list.addToFront(rightList.removeLast());
		}

		while (!leftList.isEmpty()) {
			list.addToFront(leftList.removeLast());
		}

	}

	/**
	 * CompareElement class.
	 * 
	 * @author prizal
	 *
	 * @param <T>
	 */
	public class CompareElement<T extends Comparable<T>> implements Comparator<T> {

		@Override
		public int compare(T o1, T o2) {
			// TODO Auto-generated method stub
			return (o1.compareTo(o2));
		}

	}

}
