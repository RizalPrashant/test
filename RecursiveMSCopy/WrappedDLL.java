import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 * Implementation of IUListWithListIterator wrapped around the java.util.LinkedList class.
 * ListIterators do not throw ConcurrentModificationExceptions as expected,
 * but all list methods and other iterator functionality works as necessary.
 * 
 * @author CS221
 *
 * @param <T> - class of objects stored in list
 */

public class WrappedDLL<T> implements IUListWithListIterator<T>
{
	// list where objects are stored
	private LinkedList<T> list;
	
	/**
	 * Default constructor 
	 */
	public WrappedDLL() 
	{
		list = new LinkedList<T>();
	}

	@Override
	public T removeFirst()
	{
		T element;
		try
		{
			element = list.removeFirst();
		}
		catch (NoSuchElementException e)
		{
			throw new IllegalStateException("WrappedDLL - removeFirst");
		}
		return element;
	}

	@Override
	public T removeLast()
	{
		T element;
		try
		{
			element = list.removeLast();
		}
		catch (NoSuchElementException e)
		{
			throw new IllegalStateException("WrappedDLL - removeLast");
		}
		return element;
	}

	@Override
	public T remove(T element)
	{
		int index = indexOf(element);
		if(index < 0)
		{
			throw new NoSuchElementException("WrappedDLL - remove(element)");
		}
		
		return list.remove(index);
	}

	@Override
	public T first()
	{
		T element;
		try
		{
			element = list.getFirst();
		}
		catch(NoSuchElementException e)
		{
			throw new IllegalStateException("WrappedDLL - first");
		}
		
		return element;
	}

	@Override
	public T last()
	{
		T element;
		try
		{
			element = list.getLast();
		}
		catch(NoSuchElementException e)
		{
			throw new IllegalStateException("WrappedDLL - last");
		}
		
		return element;
	}

	@Override
	public boolean contains(T target)
	{
		return list.contains(target);
	}

	@Override
	public boolean isEmpty()
	{
		return list.isEmpty();
	}

	@Override
	public int size()
	{
		return list.size();
	}

	@Override
	public void add(int index, T element)
	{
		list.add(index, element);
	}

	@Override
	public void set(int index, T element)
	{
		list.set(index, element);
	}

	@Override
	public void add(T element)
	{
		list.add(element);
	}

	@Override
	public T get(int index)
	{
		return list.get(index);
	}

	@Override
	public int indexOf(T element)
	{
		return list.indexOf(element);
	}

	@Override
	public T remove(int index)
	{
		return list.remove(index);
	}

	@Override
	public void addToFront(T element)
	{
		list.addFirst(element);
	}

	@Override
	public void addToRear(T element)
	{
		list.addLast(element);
	}

	@Override
	public void addAfter(T element, T target)
	{
		int index = list.indexOf(target);
		if(index < 0)
		{
			throw new NoSuchElementException("WrappedDLL - addAfter"); 
		}

		list.add(index + 1, element);
	}

	@Override
	public Iterator<T> iterator()
	{
		return list.iterator();
	}

	@Override
	public ListIterator<T> listIterator()
	{
		return list.listIterator();
	}

}
