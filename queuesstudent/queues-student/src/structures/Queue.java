package structures;

import java.io.File;
import java.util.NoSuchElementException;

/**************************************************************************************
 * NOTE: before starting to code, check
 * support/structures/UnboundedQueueInterface.java for detailed explanation of
 * each interface method, including the parameters, return values, assumptions,
 * and requirements
 ***************************************************************************************/
public class Queue<T> implements UnboundedQueueInterface<T> {
	public final int DEFCAP = 100;
	public T[] queue;
	public int cap, size, first, last;

	@SuppressWarnings("unchecked")
	public Queue() {
		queue = (T[]) new Object[DEFCAP];
		size = 0;
		first = last = -1;
		cap = DEFCAP;
	}

	public static void main(String[] args) {
		Queue<Integer> a = new Queue<Integer>(2);
		
		System.out.println((Integer)a.queue[0]);
		
		//Queue<Integer> b = new Queue<Integer>(a);
	}

	@SuppressWarnings("unchecked")
	public Queue(int cap) {
		queue = (T[]) new Object[cap];
		size = 0;
		first = last = -1;
		this.cap = cap;
	}

	public Queue(File[] files) {
		queue = (T[]) files;
		cap = size = files.length;
		first = 0;
		last = size - 1;
	}

	public Queue<T> combine(Queue<T> o) {
		Queue<T> out = new Queue<T>(this.cap + o.cap);
		while (this.size > 0)
			out.enqueue(this.dequeue());

		while (o.size > 0)
			out.enqueue(o.dequeue());

		return out;
	}

	public Queue(Queue<T> other) {
		Queue<T> copy = new Queue<T>(other.cap);
		if (!other.isEmpty()) {
			if (other.last >= other.first) {
				for (int i = other.first; i <= other.last; i++) {
					copy.queue[i] = other.queue[i];
				}
			} else {
				for (int i = first; i < cap; i++) {
					copy.queue[i] = other.queue[i];
				}
				for (int i = 0; i <= last; i++) {
					copy.queue[i] = other.queue[i];
				}
			}
		}
		cap = other.cap;
		queue = copy.queue;
		size = other.size;
		first = other.first;
		last = other.last;
	}

	/**
	 * Enqueue an element at the rear of the queue. Most be O(1).
	 * 
	 * @param element
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void enqueue(T element) {
		if (size == cap) {
			cap *= 2;
			T[] other = (T[]) new Object[cap];
			for (int i = 0; i < size; i++) {
				other[i] = queue[i];
			}
			this.queue = other;

		} else if (last == cap - 1)
			last = -1;
		else if (last == -1) {
			first++;
		}
		last++;
		queue[last] = element;
		size++;
	}

	/**
	 * Dequeue (i.e. remove and return) the front element of the queue. Must be
	 * O(1).
	 * 
	 * @return the front element in the queue
	 * @throws NoSuchElementException
	 *             if the queue is empty
	 */
	@Override
	public T dequeue() throws NoSuchElementException {
		if (size == 0)
			throw new NoSuchElementException();
		T data = queue[first];
		if (size == 1) {
			first = last = -1;
			size = 0;
		} else {
			if (first == cap - 1)
				first = -1;
			first++;
			size--;
		}
		return data;
	}

	/**
	 * :) Returns true if the queue contains no elements. Must be O(1).
	 */
	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	/**
	 * :) Returns the number of element in the queue. Must be O(1).
	 * 
	 * @return the number of elements stored in the queue
	 */
	@Override
	public int size() {
		return size;
	}

	/**
	 * Return (but do not remove) the front element of the queue. Must be O(1).
	 * 
	 * @return the front element in the queue
	 * @throws NoSuchElementException
	 *             if the queue is empty
	 */
	@Override
	public T peek() throws NoSuchElementException {
		if (size == 0)
			throw new NoSuchElementException();
		return queue[first];
	}

	/**
	 * Returns a new queue with the elements in reverse order. Must be O(n).
	 * 
	 * @return a reversed copy of the queue
	 */
	@Override
	public UnboundedQueueInterface<T> reversed() {
		UnboundedQueueInterface<T> out = new Queue<T>(cap);
		if (!this.isEmpty()) {
			if (last >= first) {
				for (int i = last; i >= first; i--) {
					out.enqueue(queue[i]);
				}
			} else {
				for (int i = last; i >= 0; i--) {
					out.enqueue(queue[i]);
				}
				for (int i = cap - 1; i >= first; i--) {
					out.enqueue(queue[i]);
				}
			}
		}
		return out;
	}
}

class Node<T> {
	public T data;
	public Node<T> next;

	public Node(T data) {
		this.data = data;
	}

	public Node(T data, Node<T> next) {
		this.data = data;
		this.next = next;
	}
}
