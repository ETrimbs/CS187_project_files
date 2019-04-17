package structures;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Queue;

public class BinarySearchTree<T extends Comparable<T>> implements BSTInterface<T> {
	protected BSTNode<T> root;

	/**
	 * done
	 * 
	 * @param element
	 * @return true if and only if the element is stored in the tree
	 * @throws NullPointerException
	 *             if element is null
	 */
	public boolean contains(T t) {
		return this.get(t) != null;
	}

	/**
	 * Attempts to remove one copy of an element from the tree, returning true if
	 * and only if such a copy was found and removed.
	 * 
	 * The modified tree must still obey the BST rule, though it might not be
	 * balanced.
	 * 
	 * @param element
	 * @return true if and only if an element removed
	 * @throws NullPointerException
	 *             if element is null
	 */
	public boolean remove(T t) {
		if (t == null) {
			throw new NullPointerException();
		}
		boolean result = contains(t);
		if (result) {
			root = removeFromSubtree(root, t);
		}
		return result;
	}

	private BSTNode<T> removeFromSubtree(BSTNode<T> node, T t) {
		// node must not be null
		int result = t.compareTo(node.getData());
		if (result < 0) {
			node.setLeft(removeFromSubtree(node.getLeft(), t));
			if (node.getLeft() != null) {
				node.getLeft().setParent(node);
			}
			return node;
		}
		else if (result > 0) {
			node.setRight(removeFromSubtree(node.getRight(), t));
			if (node.getRight() != null) {
				node.getRight().setParent(node);
			}
			return node;
		}
		else { // result == 0
			if (node.getLeft() == null) {
				return node.getRight();
			}
			else if (node.getRight() == null) {
				return node.getLeft();
			}
			else { // neither child is null
				T predecessorValue = getHighestValue(node.getLeft());
				node.setLeft(removeRightmost(node.getLeft()));
				if (node.getLeft() != null) {
					node.getLeft().setParent(node);
				}
				node.setData(predecessorValue);
				return node;
			}
		}
	}

	private T getHighestValue(BSTNode<T> node) {
		// node must not be null
		if (node.getRight() == null) {
			return node.getData();
		}
		else {
			return getHighestValue(node.getRight());
		}
	}

	private BSTNode<T> removeRightmost(BSTNode<T> node) {
		// node must not be null
		if (node.getRight() == null) {
			return node.getLeft();
		}
		else {
			node.setRight(removeRightmost(node.getRight()));
			if (node.getRight() != null) {
				node.getRight().setParent(node);
			}
			return node;
		}
	}

	/**
	 * done
	 * 
	 * @param element
	 * @return the element in the tree which .equals() the argument, or null if no
	 *         such element exists
	 * @throws NullPointerException
	 *             if element is null
	 */
	public T get(T t) {
		if (t == null)
			throw new NullPointerException();
		T out = null;
		Iterator<T> iter = inorderIterator();

		while (iter.hasNext()) {
			T next = iter.next();
			if (next.equals(t)) {
				out = next;
				break;
			}
		}

		return out;
	}

	public BSTNode<T> getTwo(T t) {
		if (t == null)
			throw new NullPointerException();
		BSTNode<T> out = null;
		Iterator<BSTNode<T>> iter = inorderNodeIterator();

		while (iter.hasNext()) {
			BSTNode<T> next = iter.next();
			if (next.getData().equals(t)) {
				out = next;
				break;
			}
		}

		return out;
	}

	/**
	 * done
	 * 
	 * @return the height of the tree, or -1 if the tree is empty
	 */
	@Override
	public int height() {
		if (isEmpty())
			return -1;
		return height(this.getRoot()) - 1;
	}

	private int height(BSTNode<T> current) {
		int hLeft = 0, hRight = 0;

		if (current == null)
			return 0;

		hLeft += height(current.getLeft());
		hRight += height(current.getRight());

		return 1 + (hLeft >= hRight ? hLeft : hRight);
	}

	/**
	 * Returns an Iterator that performs a preorder traversal of the tree.
	 * 
	 * The Iterator's behavior is not defined in the case that the tree is modified
	 * before the iteration is finished.
	 * 
	 * @return an Iterator over the elements in preorder
	 */
	public Iterator<T> preorderIterator() {
		return new TreeElementIterator<T>(this, TreeElementIterator.pre);
	}

	private Iterator<BSTNode<T>> preorderNodeIterator() {
		return new TreeNodeIterator<T>(this, TreeNodeIterator.pre);
	}

	/**
	 * Returns an Iterator that performs a inorder traversal of the tree.
	 * 
	 * The Iterator's behavior is not defined in the case that the tree is modified
	 * before the iteration is finished.
	 * 
	 * @return an Iterator over the elements in preorder
	 */
	public Iterator<T> inorderIterator() {
		// Queue<T> queue = new LinkedList<T>();
		// inorderTraverse(queue, root);
		// return queue.iterator();
		return new TreeElementIterator<T>(this, TreeElementIterator.in);

	}

	public Iterator<BSTNode<T>> inorderNodeIterator() {
		return new TreeNodeIterator<T>(this, TreeNodeIterator.in);
	}

	// private void inorderTraverse(Queue<T> queue, BSTNode<T> node) {
	// if (node != null) {
	// inorderTraverse(queue, node.getLeft());
	// queue.add(node.getData());
	// inorderTraverse(queue, node.getRight());
	// }
	// }

	/**
	 * Returns an Iterator that performs a postorder traversal of the tree.
	 * 
	 * The Iterator's behavior is not defined in the case that the tree is modified
	 * before the iteration is finished.
	 * 
	 * @return an Iterator over the elements in preorder
	 */
	public Iterator<T> postorderIterator() {
		return new TreeElementIterator<T>(this, TreeElementIterator.post);
	}

	private Iterator<BSTNode<T>> postorderNodeIterator() {
		return new TreeNodeIterator<T>(this, TreeNodeIterator.post);
	}

	/**
	 * Returns true if and only if this tree and the other tree have the same
	 * structure, and equivalent values at each node. Uses equals() to check for
	 * node value equivalence.
	 * 
	 * @param other
	 * @return true if and only if the trees are structure- and value-equivalent
	 * @throws NullPointerException
	 *             if other is null
	 */
	@Override
	public boolean equals(BSTInterface<T> other) {
		if (other == null)
			throw new NullPointerException();
		if (this.height() != other.height())
			return false;
		ArrayList<BSTNode<T>> this_nodes = bstlist(root, new ArrayList<BSTNode<T>>());
		ArrayList<BSTNode<T>> other_nodes = bstlist(other.getRoot(), new ArrayList<BSTNode<T>>());
		for (int i = 0; i < other_nodes.size(); i++) {
			if (!this_nodes.get(i).getData().equals(other_nodes.get(i).getData()))
				return false;
		}
		return true;
	}

	private ArrayList<BSTNode<T>> bstlist(BSTNode<T> cur, ArrayList<BSTNode<T>> temp) {
		if (cur == null) {
			return temp;
		}
		temp = bstlist(cur.getLeft(), temp);
		temp.add(cur);
		temp = bstlist(cur.getRight(), temp);
		return temp;
	}

	/**
	 * done
	 * 
	 * Returns true if and only if this tree and the other tree store the same
	 * values, regardless of structure. Uses equals() to check for stored value
	 * equivalence.
	 * 
	 * @param other
	 * @return true if and only if the trees are value-equivalent
	 * @throws NullPointerException
	 *             if other is null
	 */
	@Override
	public boolean sameValues(BSTInterface<T> other) {
		if (other == null)
			throw new NullPointerException("Other tree is null");
		if (root == null && other.getRoot() != null || other.getRoot() == null && root != null)
			return false;
		ArrayList<BSTNode<T>> this_nodes = bstlist(root, new ArrayList<BSTNode<T>>());
		ArrayList<BSTNode<T>> other_nodes = bstlist(other.getRoot(), new ArrayList<BSTNode<T>>());
		for (int i = 1; i < other_nodes.size(); i++) {
			if (this_nodes.get(i).getData() != other_nodes.get(i).getData())
				return false;
		}
		return true;
	}
	// @Override
	// public boolean sameValues(BSTInterface<T> other) {
	// if (other == null)
	// throw new NullPointerException();
	// if (this.size() != other.size())
	// return false;
	//
	// Queue<BSTNode<T>> queue = new LinkedList<BSTNode<T>>();
	// Queue<BSTNode<T>> queueOther = new LinkedList<BSTNode<T>>();
	//
	// Iterator<BSTNode<T>> iter = this.inorderNodeIterator();
	// Iterator<BSTNode<T>> iterOther = ((BinarySearchTree<T>)
	// other).inorderNodeIterator();
	//
	// while (iter.hasNext()) {
	// queue.add(iter.next());
	// queueOther.add(iterOther.next());
	// }
	//
	// Iterator<BSTNode<T>> iterFinal = queue.iterator();
	// Iterator<BSTNode<T>> iterFinalOther = queueOther.iterator();
	//
	// while (iterFinal.hasNext() && iterFinalOther.hasNext()) {
	// if (iterFinal.next() != iterFinalOther.next())
	// return false;
	// }
	//
	// return true;
	// }

	/**
	 * done
	 * 
	 * Returns true if and only if the tree is completely balanced. A balanced tree
	 * is either a full tree, or a tree that would be full if all leaves in the
	 * highest level were removed.
	 * 
	 * An empty tree is considered balanced.
	 * 
	 * @return true if and only if the tree is balanced
	 */
	@Override
	public boolean isBalanced() {
		int h = this.height(), n = this.size();

		return Math.pow(2, h) <= n && n < Math.pow(2, h + 1);
	}

	@Override
	/**
	 * Modifies the tree such that it is completely balanced.
	 * 
	 * The modified tree must still obey the BST rule, and must contain the same
	 * values it did before it was balanced.
	 */
	public void balance() {
		if (!this.isBalanced()) {
			int size = size();
			ArrayList<T> sorted = new ArrayList<T>(size);
			Iterator<T> iter = inorderIterator();
			while (iter.hasNext()) {
				sorted.add(iter.next());
			}

			this.root = null;

			balance(sorted, 0, size - 1);

			// int numElements = 1, mid = size / 2, i = 0;
			//
			// while (size > 0) {
			// numElements = (int) Math.pow(2, i);
			// for (i = 0; numElements > 0 && size > 0; i++) {
			// this.add(sorted.get(mid));
			//
			// numElements--;
			// size--;
			// }
			// }
		}
	}

	private void balance(ArrayList<T> sorted, int lower, int upper) { // inclusive bounds
		if (!(lower > upper)) {
			if (lower == upper)
				this.add(sorted.get(lower));
			else {
				int mid = (upper + lower) / 2;
				this.add(sorted.get(mid));
				balance(sorted, lower, mid - 1);
				balance(sorted, mid + 1, upper);
			}
		}
	}

	/**
	 * done
	 * 
	 * Adds an element to the tree.
	 * 
	 * The modified tree must still obey the BST rule, though it might not be
	 * balanced.
	 * 
	 * @param element
	 * @throws NullPointerException
	 *             if element is null
	 */
	public void add(T t) {
		addTwo(t);
	}

	public BSTNode<T> addTwo(T t) {
		if (t == null) {
			throw new NullPointerException();
		}
		BSTNode<T> node = new BSTNode<T>(t, null, null);
		root = addToSubtree(root, node);
		return node;
	}

	protected BSTNode<T> addToSubtree(BSTNode<T> node, BSTNode<T> toAdd) {
		if (node == null) {
			return toAdd;
		}
		int result = toAdd.getData().compareTo(node.getData());
		if (result <= 0) {
			node.setLeft(addToSubtree(node.getLeft(), toAdd));
			if (node.getLeft() != null) {
				node.getLeft().setParent(node);
			}
		}
		else {
			node.setRight(addToSubtree(node.getRight(), toAdd));
			if (node.getRight() != null) {
				node.getRight().setParent(node);
			}
		}
		return node;
	}

	/**
	 * done
	 * 
	 * @return the least value stored in this tree, or null if the tree is empty
	 */
	@Override
	public T getMinimum() {
		if (isEmpty())
			return null;
		return getMinimum(this.getRoot());
	}

	private T getMinimum(BSTNode<T> current) {
		if (current.hasLeft())
			return getMinimum(current.getLeft());
		return current.getData();
	}

	/**
	 * done
	 * 
	 * @return the greatest value stored in this tree, or null if the tree is empty
	 */
	@Override
	public T getMaximum() {
		if (isEmpty())
			return null;
		return getMaximum(this.getRoot());
	}

	private T getMaximum(BSTNode<T> current) {
		if (current.hasRight())
			return getMaximum(current.getRight());
		return current.getData();
	}

	/**
	 * done
	 * 
	 * @return true if and only if the tree contains no elements
	 */
	public boolean isEmpty() {
		return root == null;
	}

	/**
	 * done
	 * 
	 * @return the number of elements stored in the tree
	 */
	public int size() {
		return subtreeSize(root);
	}

	protected int subtreeSize(BSTNode<T> node) {
		if (node == null) {
			return 0;
		}
		else {
			return 1 + subtreeSize(node.getLeft()) + subtreeSize(node.getRight());
		}
	}

	/**
	 * (This is a utility method that you would not include in a real BST's public
	 * interface. It's here to help us grade and to help you write tests.)
	 * 
	 * @return the root BSTNode of this tree
	 */
	@Override
	public BSTNode<T> getRoot() {
		// DO NOT MODIFY
		return root;
	}

	public static <T extends Comparable<T>> String toDotFormat(BSTNode<T> root) {
		// header
		int count = 0;
		String dot = "digraph G { \n";
		dot += "graph [ordering=\"out\"]; \n";
		// iterative traversal
		Queue<BSTNode<T>> queue = new LinkedList<BSTNode<T>>();
		queue.add(root);
		BSTNode<T> cursor;
		while (!queue.isEmpty()) {
			cursor = queue.remove();
			if (cursor.getLeft() != null) {
				// add edge from cursor to left child
				dot += cursor.getData().toString() + " -> " + cursor.getLeft().getData().toString() + ";\n";
				queue.add(cursor.getLeft());
			}
			else {
				// add dummy node
				dot += "node" + count + " [shape=point];\n";
				dot += cursor.getData().toString() + " -> " + "node" + count + ";\n";
				count++;
			}
			if (cursor.getRight() != null) {
				// add edge from cursor to right child
				dot += cursor.getData().toString() + " -> " + cursor.getRight().getData().toString() + ";\n";
				queue.add(cursor.getRight());
			}
			else {
				// add dummy node
				dot += "node" + count + " [shape=point];\n";
				dot += cursor.getData().toString() + " -> " + "node" + count + ";\n";
				count++;
			}

		}
		dot += "};";
		return dot;
	}

	// public static void main(String[] args) {
	// for (String r : new String[] { "a", "b", "c", "d", "e", "f", "g" }) {
	// BSTInterface<String> tree = new BinarySearchTree<String>();
	// for (String s : new String[] { "d", "b", "a", "c", "f", "e", "g" }) {
	// tree.add(s);
	// }
	// Iterator<String> iterator = tree.inorderIterator();
	// while (iterator.hasNext()) {
	// System.out.print(iterator.next());
	// }
	// System.out.println();
	// iterator = tree.preorderIterator();
	// while (iterator.hasNext()) {
	// System.out.print(iterator.next());
	// }
	// System.out.println();
	// iterator = tree.postorderIterator();
	// while (iterator.hasNext()) {
	// System.out.print(iterator.next());
	// }
	// System.out.println();
	//
	// System.out.println(tree.remove(r));
	//
	// iterator = tree.inorderIterator();
	// while (iterator.hasNext()) {
	// System.out.print(iterator.next());
	// }
	// System.out.println();
	// }
	//
	// BSTInterface<String> tree = new BinarySearchTree<String>();
	// for (String r : new String[] { "a", "b", "c", "d", "e", "f", "g" }) {
	// tree.add(r);
	// }
	// System.out.println(tree.size());
	// System.out.println(tree.height());
	// System.out.println(tree.isBalanced());
	// tree.balance();
	// System.out.println(tree.size());
	// System.out.println(tree.height());
	// System.out.println(tree.isBalanced());
	// }
}

class TreeNodeIterator<T extends Comparable<T>> implements Iterator<BSTNode<T>> { // not done, needs to call initList
	// when the BST is updated
	ArrayList<BSTNode<T>> treeArray;
	BinarySearchTree<T> tree;
	int order, index;

	public static final int pre = 0, in = 1, post = 2;

	public TreeNodeIterator(BinarySearchTree<T> tree, int order) {
		treeArray = new ArrayList<BSTNode<T>>(tree.size() * 2);
		if (!tree.isEmpty())
			initList(tree.getRoot(), order);
		this.tree = tree;
		this.order = order;
		this.index = 0;
	}

	private void initList(BSTNode<T> head, int order) {
		if (head != null) {
			switch (order) {
			case pre:
				treeArray.add(head);
				initList(head.getLeft(), order);
				initList(head.getRight(), order);
				break;
			case in:
				initList(head.getLeft(), order);
				treeArray.add(head);
				initList(head.getRight(), order);
				break;
			case post:
				initList(head.getLeft(), order);
				initList(head.getRight(), order);
				treeArray.add(head);
				break;
			}
		}
	}

	@Override
	public boolean hasNext() {
		return index >= 0 && index < treeArray.size() && treeArray.get(index) != null;
	}

	@Override
	public BSTNode<T> next() {
		if (!hasNext())
			throw new NoSuchElementException();

		return treeArray.get(index++);
	}

	@Override
	public void remove() {
		throw new UnsupportedOperationException();
	}
}

class TreeElementIterator<T extends Comparable<T>> implements Iterator<T> { // not done, needs to call initList
	// when the BST is updated
	ArrayList<T> treeArray;
	BinarySearchTree<T> tree;
	int order, index;

	public static final int pre = 0, in = 1, post = 2;

	public TreeElementIterator(BinarySearchTree<T> tree, int order) {
		treeArray = new ArrayList<T>(tree.size() * 2);
		if (!tree.isEmpty())
			initList(tree.getRoot(), order);
		this.tree = tree;
		this.order = order;
		this.index = 0;
	}

	private void initList(BSTNode<T> head, int order) {
		if (head != null) {
			switch (order) {
			case pre:
				treeArray.add(head.getData());
				initList(head.getLeft(), order);
				initList(head.getRight(), order);
				break;
			case in:
				initList(head.getLeft(), order);
				treeArray.add(head.getData());
				initList(head.getRight(), order);
				break;
			case post:
				initList(head.getLeft(), order);
				initList(head.getRight(), order);
				treeArray.add(head.getData());
				break;
			}
		}
	}

	@Override
	public boolean hasNext() {
		return index >= 0 && index < treeArray.size() && treeArray.get(index) != null;
	}

	@Override
	public T next() {
		if (!hasNext())
			throw new NoSuchElementException();

		return treeArray.get(index++);
	}

	@Override
	public void remove() {
		throw new UnsupportedOperationException();
	}
}
