import java.util.Arrays;

public class PQueue<T> {
	
private MaxHeap<T> maxHeap;
	

public PQueue() {
	maxHeap = new MaxHeap();
}

public PQueue(T[]objects, int[]keys) {
	maxHeap = new MaxHeap(objects, keys);
}

public T maximum() {
	
	return maxHeap.heapMax();
	
}

public T extractMax(){
	
		return maxHeap.extractHeapMax();

}
public void increaseKey(int i, int k) {
	
	maxHeap.increaseHeapKey(i, k);
}

public void insert(T object, int element) {
	
	maxHeap.maxHeapInsert(object, element);
	
}

public boolean isEmpty() {
	
	return maxHeap.getHeapSize() == 0;
}

public int size() {
	
	return maxHeap.getHeapSize();
}

}