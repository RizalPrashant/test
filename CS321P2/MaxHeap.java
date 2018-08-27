import java.util.Arrays;

public class MaxHeap<T> {

	private HeapNode<T>[] heap;
	private int heapSize, capacity;
	private int DEFAULT_CAPACITY = 50;
	/**
	 *  constructor
	 */
	public MaxHeap() {
		heapSize = 0;
		capacity = DEFAULT_CAPACITY;
		heap = new HeapNode[capacity + 1];
	}
	/**
	 * constructor
	 * @param objects
	 * @param keys
	 */
	public MaxHeap(T[] objects, int[] keys) {
		int i;
		capacity = DEFAULT_CAPACITY;
		heapSize = objects.length;
		heap = new HeapNode[capacity + 1];
		for (i = 0; i < objects.length; i++) {
			HeapNode<T> heapNode = new HeapNode<T>(objects[i], keys[i]);
			heap[i + 1] = heapNode;
		}
		for(i = heap.length/2; i >= 1; i--) {
			maxHeapify(i);
		}
	}
	/**
	 * 
	 * @return highest priority process
	 */
	public T heapMax() {	
		return heap[1].getObject();
	}
	/**
	 * 
	 * @return highest priority process and removes
	 */
	public T extractHeapMax() {
		T max = heap[1].getObject();
		heap[1] = heap[1 + 1];
		maxHeapify(1);
		heapSize --;
		return max;
	}
	/**
	 * increases key of process
	 * 
	 * @param i
	 * @param k
	 */
	public void increaseHeapKey(int i, int k) {
		if (k >  heap[i].getKey()) {			
			heap[i].setKey(k);						
		}
	
		while (i > 1 && heap[parent(i)].getKey() < heap[i].getKey()){
		{
			swap(i, parent(i));
			i = parent(i);
		}
		}
	}
	/**
	 * Inserts process into right location in heap
	 * @param object
	 * @param element
	 */
	public void maxHeapInsert(T object, int element) {
		HeapNode<T> node = new HeapNode<T>(object, element);
		heap[heapSize + 1] = node;
		heapSize++;
		int parent = parent(heapSize);
		int e = heapSize;
		while (parent > 0 && heap[parent].getKey() < heap[e].getKey()) {
			swap(parent, e);
			e = parent(e);
			parent = parent(e);
		}
	}
	/**
	 * doubles the default capacity 
	 */
	public void expandCapacity() {
		//fix that
		//make new array with 2 times size
		// copy old array into new array
		//HeapNode<T>[] tempHeap = new HeapNode[2 * (capacity + 1)];
		capacity = DEFAULT_CAPACITY * 2;
	}
	/**
	 * max heapify the heap
	 * @param pos
	 */
	public void maxHeapify(int pos) {
		
		int l, r, largest = 0;

		l = left(pos);
		r = right(pos);
		if (l <= heapSize && heap[l].getKey() > heap[pos].getKey()) {
			largest = l;
		} else {
			largest = pos;
		}
		if (r <= heapSize && heap[r].getKey() > heap[largest].getKey()) {
			largest = r;
		}
		if (largest != pos) {
			swap(pos, largest);
			maxHeapify(largest);
		}

	}
	/**
	 * 
	 * @return heapSize
	 */
	public int getHeapSize() {
		
		return heapSize;
	}
	/**
	 * 
	 * @return true if empty otherwise false
	 */
	public boolean isEmpty() {
		return(heapSize == 0);
	}
	/**
	 * method for interchanging the two heaps position
	 * @param position1
	 * @param position2
	 */
	public void swap(int position1, int position2) {
		HeapNode<T> heapNode = heap[position1];
		heap[position1] = heap[position2];
		heap[position2] = heapNode;

	}
	
	/**
	 * 
	 * @param position
	 * @return parent's position
	 */
	private int parent(int position) {
		return (position / 2);
	}
	/**
	 * 
	 * @param position
	 * @return left child's position
	 */
	private int left(int position) {
		return (2 * position);
	}
	/**
	 * 
	 * @param position
	 * @return right child's position
	 */
	private int right(int position) {
		return (2 * position) + 1;
	}
	
}