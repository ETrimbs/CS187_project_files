package sorting;

import structures.Queue;

/**
 * A class containing methods to sort queues and merge sorted queues.
 * 
 * "Sorted" means in ascending order: the front of the queue is the smallest
 * element, and the rear of the queue is the largest.
 * 
 * e1 is less than or equal to e2 if and only if (e1.compareTo(e2) <= 0)
 *
 */
public class MergeSorter<T extends Comparable<T>> {
	/**
	 * Returns a new queue containing the elements from the input queue in sorted
	 * order.
	 * 
	 * Implement this method recursively:
	 * 
	 * In the base case, return the queue with no further work.
	 *
	 * Otherwise:
	 * 
	 * First, call divide to split the input queue into two smaller output queues.
	 * 
	 * Then, recursively mergeSort each of these two smaller queues.
	 * 
	 * Finally, return the result of merging these two queues.
	 * 
	 * @param queue
	 *            an input queue
	 * @return a sorted copy of the input queue
	 */
	public Queue<T> mergeSort(Queue<T> queue) {
		if (queue.size == 1)
			return queue;
		if (queue.size == 0)
			return new Queue<T>();

		Queue<T> input = new Queue<T>(queue);
		Queue<T> output1 = new Queue<T>(); // output queue 1
		Queue<T> output2 = new Queue<T>(); // output queue 2
		// TODO 1

		divide(input, output1, output2);
		output1 = mergeSort(output1);
		output2 = mergeSort(output2);
		return merge(output1, output2);
	}

	/**
	 * Split elements from the input queue into the output queues, roughly half and
	 * half.
	 * 
	 * @param input
	 *            a queue
	 * @param output1
	 *            a queue into which about half of the elements in input should go
	 * @param output2
	 *            a queue into which the other half of the elements in input should
	 *            go
	 */
	void divide(Queue<T> input, Queue<T> output1, Queue<T> output2) {
		if (input.size >= 1) {

			int size = input.size;
			int half = (size - 1) / 2;
			// int otherH = input.size - half;

			for (int i = 0; i <= half; i++) {
				output1.enqueue(input.dequeue());
			}
			for (int i = half + 1; i < size; i++) {
				output2.enqueue(input.dequeue());
			}
		}
	}

	/**
	 * Merge sorted input queues into an output queue in sorted order, and returns
	 * that queue. To do so: while there are still elements in at least one of the
	 * input queues, compare the front elements and pick the smaller among the two,
	 * dequeue it, and enqueue it to the output queue. Remember, the Queue class has
	 * a peek method which allows you to check the front element without removing
	 * it.
	 * 
	 * @param input1
	 *            a sorted queue
	 * @param input2
	 *            a sorted queue
	 * @return a sorted queue consisting of all elements from input1 and input2
	 */
	Queue<T> merge(Queue<T> input1, Queue<T> input2) {
		Queue<T> output = new Queue<T>(input1.size + input2.size + 5);

		while (input1.size > 0 && input2.size > 0) {
			if (input1.peek().compareTo(input2.peek()) <= 0) {
				output.enqueue(input1.dequeue());
			} else {
				output.enqueue(input2.dequeue());
			}
		}
		while (input1.size != 0 || input2.size != 0) {
			output.enqueue((input1.size == 0 ? input2.dequeue() : input1.dequeue()));
		}

		return output;
	}
}
