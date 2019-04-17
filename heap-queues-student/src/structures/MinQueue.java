package structures;

import comparators.ReverseIntegerComparator;

public class MinQueue<V> extends OrderedQueue<V> {

	public MinQueue() {
		store = new StudentArrayHeap<Integer, V>(new ReverseIntegerComparator());
	}
}
