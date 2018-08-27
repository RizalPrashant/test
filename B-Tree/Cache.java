import java.util.NoSuchElementException;

public class Cache<T> implements ICache<T> {

	private int cacheSize;
	private DLLNode<T> head;
	private DLLNode<T> tail;
	private int listSize = 0;
	private double numberHits ;
	private double numberMiss;
	boolean debug = false;
	
	public Cache() {
		head = null;
		tail = null;
		cacheSize = 100;
		numberHits = 0;
		numberMiss = 0;
	}

	public Cache(int size) {
		head = null;
		tail = null;
		cacheSize = size;
		numberHits = 0;
		numberMiss = 0;
		if (cacheSize == 0) {
			System.exit(1);
		}
	}
	// Method created to find an element in linked list
	/**
	 * find an element in the cache
	 * @param data-object of type T
	 * @return boolean
	 */
	public DLLNode<T> findNode(T data) {
		DLLNode<T> temp;
		temp = head;
		if(debug) System.out.println("Searching for [" + data + "]");
		while(temp!= null) {
			if(debug) System.out.println(temp.getElement());
			if (temp.getElement().equals(data)) {
				return temp;
			}
			temp = temp.getNext();
		}
		if(debug) System.out.println();
		return null;
	}
	
	private DLLNode<T> removeNode(DLLNode node){
		//TODO: Remove next and previous pointers
		if (head == tail) {
			head = null;
			tail = null;
		}
		else if (node == head) {
			head = node.getNext();
			node.getNext().setPrevious(null);
			node.setNext(null);
		}
		else if(node == tail) {
			tail = node.getPrevious();
			node.getPrevious().setNext(null);
			node.setPrevious(null);
		}
		else {
		node.getPrevious().setNext(node.getNext());
		node.getNext().setPrevious(node.getPrevious());
		node.setNext(null);
		node.setPrevious(null);
		}
		return node;
	}
	
	private void addNode(DLLNode node) {
		//TODO: make this head node
		if (head == null) {
			head = node;
			tail = node;
		}
		head.setPrevious(node);
		node.setNext(head);
		head = node;
		
	}
	/**
	 * Gets the data from cache and moves it to the front, if it's there.
	 * If not, returns null reference. 
	 * @param target - object of type T
	 * @return object of type T, or null reference 
	 */
	@Override
	public T get(T target) {
		DLLNode<T> tmp;
		tmp = head;
		if(tmp == null) {
			numberMiss++;
			return null;
		}
		//System.out.println("Test: " + tmp.getElement());
		//System.out.println("Test1: " + target);
		if ((tmp = findNode(target)) != null) {
			addNode(removeNode(tmp));
			numberHits++;
			
			//System.out.println(head.getElement());
			return target;
		}
		//System.out.println("null");
		numberMiss++;
		return null;
	}
	//Tested
	@Override
	/***
	 * Clears contents of the cache,
	 * but doesn't change its capacity. 
	 */
	public void clear() {
		DLLNode<T> tmp;
		tmp = tail;
		while (tmp != null) {
			removeLast();
			tmp = tmp.getPrevious();
		}
		//System.out.println(tail.getElement());
	}
	// Tested
	@Override
	/***
	 * Adds given data to front of cache. 
	 * Removes data in last position, if full.
	 * @param data - object of type T
	 */
	public void add(T data) {
			T obtained = get(data);
			if (obtained == data) {
				return;
			}
			if(listSize >= cacheSize) {
				removeLast();
			}
			DLLNode<T> curr = new DLLNode<T>(data);
			if(head == null) {
				head = curr;
				tail = curr;
				listSize++;
			}
			else {
			curr.setNext(head);
			head.setPrevious(curr);
			head = curr;
			listSize++;
			}
			//System.out.println(head.getElement());
			//System.out.println(listSize);
	}
	//Tested
	@Override
	/***
	 * Removes data in last position in cache.
	 * @throws IllegalStateException - if cache is empty. 
	 */
	public void removeLast() {
		if (head == null && tail == null) {
			throw new IllegalStateException("Empty Cache");
		}
		if (head == tail) {
			head = null;
			tail = null;
			listSize--;
		}
		else {
		tail.getPrevious().setNext(null);
		tail = tail.getPrevious();
		listSize--;
		}
	}
	/**
	 * Removes the given target data from the cache.
	 * @throws NoSuchElementException - if target not found 
	 * @param target - object of type T 
	 */
	@Override
	public void remove(T target) {
		DLLNode<T> curr = findNode(target);
		DLLNode<T> temp = null;
		if (head == null && tail == null) {
			throw new NoSuchElementException("Cant find element set empty");
		}
		else if (curr == null) {
			throw new NoSuchElementException("Cant find element its not in the cache");
		}
		else {
			removeNode(curr);
			listSize--;
			//System.out.println(listSize);
		}
		
	}
	/**
	 * Moves data already in cache to the front. 
	 * @throws NoSuchElementException - if data not in cache   
	 * @param data - object of type T
	 */
	@Override
	public void write(T data) {
		DLLNode<T> temp = findNode(data);
		if (temp == null) {
			throw new NoSuchElementException("Cant find element its not in the cache");
		}
		temp = removeNode(temp);
		temp.setNext(head);
		head.setPrevious(temp);
		head = temp;
		//System.out.println("null");
	}
	public double getNumHits() {
		return numberHits;
	}
	public double getNumMiss() {
		return numberMiss;
	}
	
	@Override
	/**
	 * Get hit rate of the cache.
	 * @return double value 
	 */
	public double getHitRate() {
		double hitRate = numberHits / (numberHits + numberMiss) ;
//		System.out.println(numberHits);
//		System.out.println(numberAccess);
//		System.out.println(hitRate);
		return hitRate ;
	}
	/**
	 * Get miss rate of the cache.  
	 * @return double value 
	 */
	@Override
	public double getMissRate() {
		double hitRate = numberHits / (numberHits + numberMiss) ;
		double missRate =  1 - hitRate;
		//System.out.println(numberMiss);
		//System.out.println(numberAccess);
		//System.out.println(missRate);
		return missRate;
	}

	@Override
	/**
	 * Whether there's any data in cache. 
	 * @return boolean value 
	 */
	public boolean isEmpty() {
		if (listSize == 0) {
			//System.out.println("True");
			return true;
		}
		//System.out.println("False");
		return false;
	}
	
	public String toString() {
		return " " + cacheSize + " ";
	}

}