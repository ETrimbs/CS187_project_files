import java.util.NoSuchElementException;

public class LinkedDeque<T> implements Deque<T> {

	private DLNode<T> head;
	private DLNode<T> tail;

	@Override
	public void addToFront(T element) {
		head = new DLNode<T>(element, head, null);
		if (head.next == null) {
			tail = head;
		} else {
			head.next.prev = head;
		}
	}

	public static void main(String[] args) {

		LinkedDeque<Integer> a = new LinkedDeque<Integer>();

		a.addToRear(2);
		a.addToFront(3);
		a.addToFront(4);
		a.addToFront(5);
		a.addToRear(2);
		a.addToRear(1);
		System.out.print(a.removeFront());
		System.out.print(a.removeFront());
		System.out.print(a.removeRear());
		System.out.print(a.removeRear());
		System.out.print(a.removeRear());
		System.out.print(a.last());
		System.out.print(a.removeRear());
		
		String test = "";
		String test2 = test;


	}

	@Override
	public T removeFront() throws NoSuchElementException {
		if (head == null) {
			throw new NoSuchElementException();
		}
		
		T data = head.data;
		
		if (head.next != null) {
			head.next.prev = null;
			head = head.next;
		} else {
			tail = null;
			head = null;
		}
		
		return data;
	}

	@Override
	public T first() throws NoSuchElementException {
		if (isEmpty()) {
			throw new NoSuchElementException();
		}
		return head.data;
	}

	@Override
	public void addToRear(T element) {
		if (tail == null) {
			tail = new DLNode<T>(element, null, null);
			head = tail;
		} else {
			tail.next = new DLNode<T>(element, null, tail);
			tail = tail.next;
		}
	}

	@Override
	public T removeRear() throws NoSuchElementException {
		if (tail == null)
			throw new NoSuchElementException();

		T data = tail.data;

		if (tail.prev == null) {
			tail = null;
			head = null;
		} else {
			tail = tail.prev;
			tail.next = null;
		}

		return data;

	}

	@Override
	public T last() throws NoSuchElementException {
		if (tail == null)
			throw new NoSuchElementException();
		return tail.data;
	}

	@Override
	public boolean isEmpty() {
		return head == null;
	}

}