package structures;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class BinarySearchTree2<T extends Comparable<T>> implements
BSTInterface<T> {
	protected BSTNode<T> root;
	
	public ArrayList<BSTNode<T>> nodes = bstlist(root, new ArrayList<BSTNode<T>>());

	public boolean isEmpty() {
		return root == null;
	}

	public int size() {
		return subtreeSize(root);
	}

	protected int subtreeSize(BSTNode<T> node) {
		if (node == null) {
			return 0;
		} else {
			return 1 + subtreeSize(node.getLeft())
			+ subtreeSize(node.getRight());
		}
	}

	public boolean contains(T t) {
		if(t == null)
			throw new NullPointerException("Element is null");
		BSTNode<T> cur = root;
		while(cur != null)
		{
			if(cur.getData() == t)
				return true;
			if(cur.getData().compareTo(t) >= 1)
				cur = cur.getLeft();
			else
				cur = cur.getRight();
		}
		return false;
	}

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
			return node;
		} else if (result > 0) {
			node.setRight(removeFromSubtree(node.getRight(), t));
			return node;
		} else { // result == 0
			nodes.remove(node);
			if (node.getLeft() == null) {
				return node.getRight();
			} else if (node.getRight() == null) {
				return node.getLeft();
			} else { // neither child is null
				T predecessorValue = getHighestValue(node.getLeft());
				node.setLeft(removeRightmost(node.getLeft()));
				node.setData(predecessorValue);
				return node;
			}
		}
	}

	private T getHighestValue(BSTNode<T> node) {
		// node must not be null
		if (node.getRight() == null) {
			return node.getData();
		} else {
			return getHighestValue(node.getRight());
		}
	}

	private T getLowestValue(BSTNode<T> node) {
		// node must not be null
		if(node == null)
			return null;
		if (node.getLeft() == null) {
			return node.getData();
		} else {
			return getLowestValue(node.getLeft());
		}
	}

	private BSTNode<T> removeRightmost(BSTNode<T> node) {
		// node must not be null
		if (node.getRight() == null) {
			nodes.remove(node);
			return node.getLeft();
		} else {
			node.setRight(removeRightmost(node.getRight()));
			return node;
		}
	}

	public T get(T t) {
		if(t == null)
			throw new NullPointerException("Element is null");
		ArrayList<T> nodes = bstListData(root, new ArrayList<T>());
		for(T data : nodes)
		{
			if(data == t)
				return t;
		}
		return null;
	}


	public void add(T t) {
		if (t == null) {
			throw new NullPointerException();
		}
		root = addToSubtree(root, new BSTNode<T>(t, null, null));
	}

	protected BSTNode<T> addToSubtree(BSTNode<T> node, BSTNode<T> toAdd) {
		if (node == null) {
			nodes.add(toAdd);
			return toAdd;
		}
		int result = toAdd.getData().compareTo(node.getData());
		if (result <= 0) {
			node.setLeft(addToSubtree(node.getLeft(), toAdd));
		} else {
			node.setRight(addToSubtree(node.getRight(), toAdd));
		}
		return node;
	}

	@Override
	public T getMinimum() {
		if(root == null)
			return null;
		return getLowestValue(root);
	}


	@Override
	public T getMaximum() {
		if(root == null)
			return null;
		return getHighestValue(root);
	}

	private int heightHelper(BSTNode<T> node, int i)
	{
		if (node == null || (node.getLeft() == null && node.getRight() == null)) 
			return i;
		return Math.max(heightHelper(node.getLeft(), i+1), heightHelper(node.getRight(), i+1));
	}

	@Override
	public int height() {
		if(root == null)
			return -1;
		return heightHelper(root,0);
	}


	public Iterator<T> preorderIterator() {
		Queue<T> queue = new LinkedList<T>();
		preorderTraverse(queue, root);
		return queue.iterator();
	}

	private void preorderTraverse(Queue<T> queue, BSTNode<T> node) {
		if (node != null) {
			queue.add(node.getData());
			preorderTraverse(queue, node.getLeft());
			preorderTraverse(queue, node.getRight());
		}
	}


	public Iterator<T> inorderIterator() {
		Queue<T> queue = new LinkedList<T>();
		inorderTraverse(queue, root);
		return queue.iterator();
	}


	private void inorderTraverse(Queue<T> queue, BSTNode<T> node) {
		if (node != null) {
			inorderTraverse(queue, node.getLeft());
			queue.add(node.getData());
			inorderTraverse(queue, node.getRight());
		}
	}

	public Iterator<T> postorderIterator() {
		Queue<T> queue = new LinkedList<T>();
		postorderTraverse(queue, root);
		return queue.iterator();
	}

	private void postorderTraverse(Queue<T> queue, BSTNode<T> node) {
		if (node != null) {
			postorderTraverse(queue, node.getLeft());
			postorderTraverse(queue, node.getRight());
			queue.add(node.getData());
		}
	}

	private ArrayList<BSTNode<T>> bstlist(BSTNode<T> cur, ArrayList<BSTNode<T>> temp)
	{
		if(cur == null)
		{
			return temp;
		}
		bstlist(cur.getLeft(), temp);
		temp.add(cur);
		return bstlist(cur.getRight(), temp);
	}


	@Override
	public boolean equals(BSTInterface<T> other) {
		if(other == null)
			throw new NullPointerException("Other tree is null");
		if(this.height() != other.height())
			return false;
		ArrayList<BSTNode<T>> this_nodes = bstlist(root, new ArrayList<BSTNode<T>>());
		ArrayList<BSTNode<T>> other_nodes = bstlist(other.getRoot(), new ArrayList<BSTNode<T>>());
		for(int i = 1; i < other_nodes.size(); i++)
		{
			if(this_nodes.get(i).getData() != other_nodes.get(i).getData())
				return false;
		}
		return true;
	}


	@Override
	public boolean sameValues(BSTInterface<T> other) {
		if(other == null)
			throw new NullPointerException("Other tree is null");
		if(root == null && other.getRoot() != null || other.getRoot() == null && root != null)
			return false;
		ArrayList<BSTNode<T>> this_nodes = bstlist(root, new ArrayList<BSTNode<T>>());
		ArrayList<BSTNode<T>> other_nodes = bstlist(other.getRoot(), new ArrayList<BSTNode<T>>());
		for(int i = 1; i < other_nodes.size(); i++)
		{
			if(this_nodes.get(i).getData() != other_nodes.get(i).getData())
				return false;
		}
		return true;
	}

	@Override
	public boolean isBalanced() {
		if(root.getLeft() == null && root.getRight() == null)
			return true;
		return ((Math.pow(2, height()) <= (size())) && ((size()) < Math.pow(2, (height()+1))));
	}

	@Override
	@SuppressWarnings("unchecked")

	public void balance() 
	{
		T[] nodes = (T[]) new Comparable[size()];
		ArrayList<T> node_arraylist = bstListData(root, new ArrayList<T>());
		for(int i = 0; i < nodes.length; i++)
		{
			nodes[i] = node_arraylist.get(i);
		}
		Arrays.sort(nodes);
		root = sortedArrayToBST(nodes, 0, nodes.length-1);
	}
	
	protected ArrayList<T> bstListData(BSTNode<T> cur, ArrayList<T> temp)
	{
		if(cur == null)
		{
			return temp;
		}
		bstListData(cur.getLeft(), temp);
		temp.add(cur.getData());
		return bstListData(cur.getRight(), temp);
	}
	
	protected BSTNode<T> sortedArrayToBST(T array[], int start, int end) 
	{
	    if (start>end) return null;	// if the range has crossed over
	    int mid = (start+end)/2;	// find middle element
	    BSTNode<T> node = new BSTNode<T>(array[mid], null, null);	// construct node
	    node.setLeft(sortedArrayToBST(array, start, mid-1));	// recursively build left subtree
	    node.setRight(sortedArrayToBST(array, mid+1, end));	// recursively build right subtree
	    return node;
	}


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
				dot += cursor.getData().toString() + " -> "
						+ cursor.getLeft().getData().toString() + ";\n";
				queue.add(cursor.getLeft());
			} else {
				// add dummy node
				dot += "node" + count + " [shape=point];\n";
				dot += cursor.getData().toString() + " -> " + "node" + count
						+ ";\n";
				count++;
			}
			if (cursor.getRight() != null) {
				// add edge from cursor to right child
				dot += cursor.getData().toString() + " -> "
						+ cursor.getRight().getData().toString() + ";\n";
				queue.add(cursor.getRight());
			} else {
				// add dummy node
				dot += "node" + count + " [shape=point];\n";
				dot += cursor.getData().toString() + " -> " + "node" + count
						+ ";\n";
				count++;
			}

		}
		dot += "};";
		return dot;
	}

	public static void main(String[] args) {
		for (String r : new String[] { "a", "b", "c", "d", "e", "f", "g" }) {
			BSTInterface<String> tree = new BinarySearchTree<String>();
			for (String s : new String[] { "d", "b", "a", "c", "f", "e", "g" }) {
				tree.add(s);
			}
			Iterator<String> iterator = tree.inorderIterator();
			while (iterator.hasNext()) {
				System.out.print(iterator.next());
			}
			System.out.println();
			iterator = tree.preorderIterator();
			while (iterator.hasNext()) {
				System.out.print(iterator.next());
			}
			System.out.println();
			iterator = tree.postorderIterator();
			while (iterator.hasNext()) {
				System.out.print(iterator.next());
			}
			System.out.println();

			System.out.println(tree.remove(r));

			iterator = tree.inorderIterator();
			while (iterator.hasNext()) {
				System.out.print(iterator.next());
			}
			System.out.println();
		}

		BSTInterface<String> tree = new BinarySearchTree<String>();
		for (String r : new String[] { "a", "b", "c", "d", "e", "f", "g" }) {
			tree.add(r);
		}
		System.out.println(tree.size());
		System.out.println(tree.height());
		System.out.println(tree.isBalanced());
		tree.balance();
		System.out.println(tree.size());
		System.out.println(tree.height());
		System.out.println(tree.isBalanced());
	}
}