package structures;

import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public abstract class OrderedQueue<V> implements PriorityQueue<Integer, V> {

	protected StudentArrayHeap<Integer, V> store;

	/**
	 * Enqueues the specified {@code value} into this {@link PriorityQueue} with the
	 * specified {@code priority}. This runs in O(log(size)) time. For convenience
	 * this method returns the modified {@link PriorityQueue}.
	 * 
	 * @param priority
	 *            the priority of this enqueue
	 * @param value
	 *            the value being enqueued
	 * @return the modified {@link PriorityQueue}.
	 * @throws NullPointerException
	 *             if {@code prioirty} is {@code null} or {@code value} is
	 *             {@code null}.
	 */
	@Override
	public PriorityQueue<Integer, V> enqueue(Integer priority, V value) {
		if (priority == null || value == null)
			throw new NullPointerException();

		store.add(priority, value);
		return this;
	}

	/**
	 * Removes the value with the highest priority in this {@link PriorityQueue} and
	 * then returns it. This runs in O(log(size)) time.
	 * 
	 * @return the value with the highest priority in this {@link PrioirtyQueue}
	 * @throws IllegalStateException
	 *             if this {@link PriorityQueue} is empty.
	 */
	@Override
	public V dequeue() {
		if (isEmpty())
			throw new IllegalStateException();

		return store.remove();
	}

	/**
	 * Returns the value with the highest priority in this {@link PriorityQueue}.
	 * 
	 * @return the value with the highest priority in this {@link PriorityQueue}.
	 * @throws IllegalStateException
	 *             if this {@link PriorityQueue} is empty.
	 */
	@Override
	public V peek() {
		if (isEmpty())
			throw new IllegalStateException();

		return store.peek();
	}

	/**
	 * Returns an {@link Iterator} over all of the entries in this
	 * {@link PriorityQueue}. The order of these elements is unspecified and a call
	 * to {@link Iterator#remove()} results in an
	 * {@link UnsupportedOperationException}.
	 * 
	 * @return an {@link Iterator} over all of the values in this
	 *         {@link PriorityQueue}.
	 */
	@Override
	public Iterator<Entry<Integer, V>> iterator() {
		return new QueueIterator<Integer, V>(store);
	}

	/**
	 * Returns the {@link Comparator} that is used to determine the ordering of
	 * {@link Entry}s in this {@link PriorityQueue}.
	 * 
	 * @return the {@link Comparator} that is used to determine the ordering of
	 *         {@link Entry}s in this {@link PriorityQueue}.
	 */
	@Override
	public Comparator<Integer> getComparator() {
		return store.getComparator();
	}

	/**
	 * Returns the total number of elements in this {@link PriorityQueue}
	 * 
	 * @return the total number of elements in this {@link PriorityQueue}
	 */
	@Override
	public int size() {
		return store.size();
	}

	/**
	 * Returns {@code true} if this {@link PrioirtyQueue} has no elements and
	 * {@code false} otherwise.
	 * 
	 * @return {@code true} if this {@link PrioirtyQueue} has no elements and
	 *         {@code false} otherwise.
	 */
	@Override
	public boolean isEmpty() {
		return store.isEmpty();
	}
}

class QueueIterator<P, V> implements Iterator<Entry<P, V>> {
	List<Entry<P, V>> list;
	int index;

	public QueueIterator(AbstractArrayHeap<P, V> heap) {
		list = heap.asList();
		index = 0;
	}

	@Override
	public boolean hasNext() {
		if (index < list.size())
			return true;
		return false;
	}

	@Override
	public Entry<P, V> next() {
		return list.get(index++);
	}

	@Override
	public void remove() {
		throw new UnsupportedOperationException();
	}

}