package structures;

import java.util.Comparator;

/**
 * An {@link AbstractArrayHeap} is an implementation of a heap that represents
 * the tree using an array. By doing this, we can easily maintain the complete
 * tree property required by a Heap.
 * 
 * @author jcollard
 * 
 * @param <P>
 *            - the type of the priority values
 * @param <V>
 *            - the type of the associated values
 * @see http://en.wikipedia.org/wiki/Heap_(data_structure)
 */
public class StudentArrayHeap<P, V> extends AbstractArrayHeap<P, V> {

	// protected final ArrayList<Entry<P, V>> heap;
	// protected final Comparator<P> comparator;

	/**
	 * Given an index of some "node" returns the index to that "nodes" left child.
	 * 
	 * @param index
	 *            an index in this {@link AbstractArrayHeap}
	 * @return the index of the specified "nodes" left child
	 * @throws IndexOutOfBoundsException
	 *             if {@code index} is less than 0
	 */
	protected int getLeftChildOf(int index) {
		if (index < 0)
			throw new IndexOutOfBoundsException();
		return index * 2 + 1;
	}

	/**
	 * Given an index of some "node" returns the index to that "nodes" right child.
	 * 
	 * @param index
	 *            a "nodes" index
	 * @return the index of the specified "nodes" right child
	 * @throws IndexOutOfBoundsException
	 *             if {@code index} is less than 0
	 */
	protected int getRightChildOf(int index) {
		if (index < 0)
			throw new IndexOutOfBoundsException();
		return index * 2 + 2;
	}

	/**
	 * Given an index of some "node" returns the index to that "nodes" parent.
	 * 
	 * @param index
	 *            a "nodes" index
	 * @return the index of the specified "nodes" parent
	 * @throws IndexOutOfBoundsException
	 *             if {@code index} is less than 1
	 */
	protected int getParentOf(int index) {
		if (index < 1)
			throw new IndexOutOfBoundsException();
		return (index - 1) / 2;
	}

	/**
	 * <p>
	 * This results in the entry at the specified index "bubbling up" to a location
	 * such that the property of the heap are maintained. This method should run in
	 * O(log(size)) time.
	 * </p>
	 * <p>
	 * Note: When add is called, an Entry is placed at the end of the internal array
	 * and then this method is called on that index.
	 * </p>
	 * 
	 * @param index
	 *            the index to bubble up
	 */
	protected void bubbleUp(int index) {
		if (index > 0) {
			int parentI = getParentOf(index);
			Entry<P, V> parent = heap.get(parentI), current = heap.get(index);
			if (comparator.compare(current.getPriority(), parent.getPriority()) > 0) {
				swap(index, parentI);
				bubbleUp(parentI);
			}
		}
	}

	/**
	 * <p>
	 * This method results in the entry at the specified index "bubbling down" to a
	 * location such that the property of the heap are maintained. This method
	 * should run in O(log(size)) time.
	 * </p>
	 * <p>
	 * Note: When remove is called, if there are elements remaining in this
	 * {@link AbstractArrayHeap} the bottom most element of the heap is placed at
	 * the 0th index and bubbleDown(0) is called.
	 * </p>
	 * 
	 * @param index
	 */
	protected void bubbleDown(int index) {
		int leftChildI = getLeftChildOf(index), rightChildI = leftChildI + 1, size = heap.size();
		
			if (rightChildI < size && comparator.compare(heap.get(leftChildI).getPriority(),
					heap.get(rightChildI).getPriority()) < 0) {
				bubbleDownH(index, rightChildI);
			}
			else if (leftChildI < size) {
				bubbleDownH(index, leftChildI);
			}
		
	}

	private void bubbleDownH(int index, int otherI) {
		if (comparator.compare(heap.get(otherI).getPriority(), heap.get(index).getPriority()) > 0) {
			swap(index, otherI);
			bubbleDown(otherI);
		}
	}

	/**
	 * Creates an {@link AbstractArrayHeap} with the specified {@link Comparator}
	 * for determining priority
	 * 
	 * @param comparator
	 *            the {@link Comparator} to use to determine priority
	 * @throws NullPointerException
	 *             if {@code comparator} is {@code null}
	 */
	protected StudentArrayHeap(Comparator<P> comparator) {
		super(comparator);
	}
}
