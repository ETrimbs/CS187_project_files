package binary_search;

import java.util.ArrayList;

public class BST<T extends Comparable<T>> {
	private BSTNode<T> root;

	public BST() {
		root = null;
	}

	public BST(BSTNode<T> root) {
		this.root = root;
	}

	public BST(T[] sorted) {
		/* This constructor builds a BST from a sorted array */
		root = sortedArrayToBST(sorted, 0, sorted.length - 1);
	}

	/*
	 * TODO: implement an iterative method (i.e. using a loop) that searches for a
	 * element in the BST. In lecture slides we covered a recursive version. Here
	 * you must implement it using a loop and no recursion. Return null if the
	 * element does not exist; or the node containing the element if it exists.
	 * Remember: for generic type T, you cannot use < or >, instead you must use the
	 * compareTo or equals method.
	 */
	public BSTNode<T> search(T elem) {
		BSTNode<T> current = root;

		while (current != null && elem.compareTo(current.data) != 0) {
			if (elem.compareTo(current.data) > 0)
				current = current.right;
			else
				current = current.left;
		}

		return current;
	}

	/*
	 * TODO: implement a method that checks whether this is a valid BST. There are
	 * at least two solutions. You can implement either. You are allowed to use
	 * recursion for this question. If you create any new method, you must include
	 * it in the written document.
	 * 
	 * In the first solution, perform an in-order traversal and save the traversed
	 * elements in a Java ArrayList, then use the ArrayList to help you check
	 * whether this is a valid BST or not (think about how). This solution is easy
	 * to implement but requires O(N) memory.
	 * 
	 * In the second solution, you can avoid using additional memory such as an
	 * ArrayList. Instead, make use of the definition of a BST to implement the
	 * validity checking. Hint: during recursion, you can pass minimum and maximum
	 * values down to the recursive calls to help you track what's the allowed range
	 * of values of the children node. Alternatively, you can have the recursive
	 * method return the minimum and maximum values of each subtree, and use those
	 * values to track the allowed range of values of the the parent.
	 */

	/**
	 * @return if this BST is a valid BST
	 */
	public boolean isValid() {
		return isValid(root, null, null);
	}

	/**
	 * Initial call: Pass the root node, min and max as null to keep track if they
	 * have ever been changed from the root's original value, useful in edge cases
	 * of far-right and far-left leaves and the path to them, direction is
	 * irrelevant.
	 * 
	 * @param current
	 *            the current node being verified for validity in this BST
	 * @param min
	 *            the min value the current node can possess while not breaking the
	 *            rules of the BST structure. null indicates no bound.
	 * @param max
	 *            the max value the current node can possess while not breaking the
	 *            rules of the BST structure. null indicates no bound.
	 */
	private boolean isValid(BSTNode<T> current, T min, T max) {
		if (current == null) // base case, arrived at a leaf or BST with null root node
			return true;

		boolean out = true; // for code simplification

		if (max != null) // if there is a max bound, check current is within
			out = out && current.data.compareTo(max) <= 0; // <= for duplicate values
		
		if (min != null) // if there is a min bound, check current is within
			out = out && current.data.compareTo(min) > 0;

		return out && isValid(current.left, min, current.data) && isValid(current.right, current.data, max);
		// when we call on the left child, the current node's value is the new upper
		// bound, and when we call on right child, the value is the new lower bound.
	}

	/* -------------------------------------------------- */
	/* YOU DO NOT NEED TO MODIFY ANY CODE BELOW THIS LINE */
	/* -------------------------------------------------- */
	private BSTNode<T> sortedArrayToBST(T array[], int start, int end) {
		if (start > end)
			return null; // if the range has crossed over
		int mid = (start + end) / 2; // find middle element
		BSTNode<T> node = new BSTNode<T>(array[mid]); // construct node
		node.left = sortedArrayToBST(array, start, mid - 1); // recursively build left subtree
		node.right = sortedArrayToBST(array, mid + 1, end); // recursively build right subtree
		return node;
	}

	public int size() {
		return sizeHelper(root);
	}

	private int sizeHelper(BSTNode<T> node) {
		if (node == null)
			return 0;
		return 1 + sizeHelper(node.left) + sizeHelper(node.right);
	}
}
