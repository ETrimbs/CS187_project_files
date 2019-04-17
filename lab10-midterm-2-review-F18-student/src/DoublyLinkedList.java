
/**
 * Implement a link-hopping method for finding the middle node of a doubly
 * linked list with head and tail pointers, and an odd number of nodes. Note
 * that you should only use link-hopping (cannot use a counter, must follow
 * next,prev references only).
 *
 * If the list has an EVEN number of elements, return null
 * 
 * @author jrondeau
 */
public class DoublyLinkedList<T> {
	private DLNode<T> head;
	private DLNode<T> tail;
	private int size;

	public DoublyLinkedList() {
		size = 0;
	}

	/**
	 * @TODO: Implement this method!
	 * @return T: data element held in the middle node of an odd-sized list. NULL if
	 *         the list is even length
	 */
	public T findMiddle() {
		if (getLength() % 2 == 0)
			return null;

		DLNode<T> start = head, end = tail;

		while (start != end) {
			start = start.next;
			end = end.prev;
		}
		return start.data;
	}

	/**
	 * @TODO
	 * @param a
	 *            - DoublyLinkedList<Float>
	 * @return Median number of list as a float
	 */
	public static float findMedian(DoublyLinkedList<Float> a) {
		if (a.getLength() % 2 == 1) {
			return a.findMiddle();
		}

		DLNode<Float> temp;
		float middle1, middle2;

		temp = a.head;
		a.head = a.head.next;
		a.size -= 1;
		middle1 = a.findMiddle();
		a.head = temp;
		a.size += 1;

		temp = a.tail;
		a.tail = a.tail.prev;
		a.size -= 1;
		middle2 = a.findMiddle();
		a.tail = temp;
		a.size += 1;

		return (middle1 + middle2) / 2;

	}

	/**
	 * @param element
	 *            Data to append to the list
	 */
	public void add(T element) {
		if (head == null) {
			head = new DLNode<T>(element, null, null);
			tail = head;
		}
		else {
			tail.next = new DLNode<T>(element, null, tail);
			tail = tail.next;
		}

		size++;
	}

	/**
	 * 
	 * @return Length of the list
	 */
	public int getLength() {
		return size;
	}

	/**
	 * Prints out all elements of the list in order from head to tail
	 */
	public void printList() {
		DLNode<T> temp = head;
		while (temp != null) {
			System.out.print(temp.data.toString() + " ");
			temp = temp.next;
		}
	}

}