package structures;

import comparators.IntegerComparator;

public class MaxQueue<V> extends OrderedQueue<V> {
	public MaxQueue() {
		store = new StudentArrayHeap<Integer, V>(new IntegerComparator());
	}
}