package sets;

import java.util.Iterator;

/**
 * A LinkedList-based implementation of Set
 */

/********************************************************
 * NOTE: Before you start, check the Set interface in
 * Set.java for detailed description of each method.
 *******************************************************/

/********************************************************
 * NOTE: for this project you must use linked lists
 * implemented by yourself. You are NOT ALLOWED to use
 * Java arrays of any type, or any Collection-based class 
 * such as ArrayList, Vector etc. You will receive a 0
 * if you use any of them.
 *******************************************************/

/********************************************************
 * NOTE: you are allowed to add new methods if necessary, but do NOT add new
 * files (as they will be ignored).
 *******************************************************/

public class LinkedSet<E> implements Set<E> {
	private LinkedNode<E> head = null;
	private Iterator<E> iter;

	public static void main(String[] args) {
		Set<Integer> set = new LinkedSet<Integer>(
				new LinkedNode<Integer>(null, new LinkedNode<Integer>(100, new LinkedNode<Integer>(9, null))));
		// LinkedSet<Integer> set = new LinkedSet<Integer>(new LinkedNode<Integer>(null,
		// null));

		Set<Integer> other = new LinkedSet<Integer>(new LinkedNode<Integer>(9, new LinkedNode<Integer>(5,
				new LinkedNode<Integer>(6, new LinkedNode<Integer>(7, new LinkedNode<Integer>(8, null))))));

		// System.out.println(set.contains(null));

		System.out.println("this: " + set.toString());
		System.out.println("that: " + other.toString());
		System.out.println("Union: " + set.union(other).toString());
		System.out.println("Intersect: " + set.intersect(other).toString());
		System.out.println("Subtract: " + set.subtract(other).toString());
		System.out.println("Remove 9: " + set.remove(9).toString());

	}

	/**
	 * Returns a new <code>Set</code> that is the intersection of
	 * <code>this Set</code> and <code>that Set</code>. This is intended to be the
	 * mathematical operation of intersection on sets.
	 *
	 * @param other
	 *            the other <code>Set</code> to take the intersection with
	 * @return a <code>Set</code> containing the intersection of the two input
	 *         <code>Set</code>s
	 */
	@Override
	public Set<E> intersect(Set<E> that) {
		Set<E> out = new LinkedSet<E>();

		for (E element : this) {
			if (that.contains(element))
				out = out.adjoin(element);
		}

		return out;
	}

	/**
	 * Returns a new <code>Set</code> that is the difference of
	 * <code>this Set</code> and <code>that Set</code>, i.e., <code>
	 * this - that</code>. This is intended to be the mathematical operation of
	 * difference on sets. Formally, the returned set contains every element
	 * <code>e</code> of <code>this</code> that is NOT an element of
	 * <code>that</code>.
	 *
	 * @param that
	 *            the <code>Set</code> to subtract from this one
	 * @return a <code>Set</code> containing the difference of the two input
	 *         <code>Set</code>s
	 */
	@Override
	public Set<E> subtract(Set<E> that) {
		Set<E> out = new LinkedSet<E>();

		for (E element : this) {
			if (!that.contains(element))
				out = out.adjoin(element);
		}

		return out;
	}

	/**
	 * Remove an element from a set. If this set does not contain the specified
	 * element then return the original set. Otherwise, return a new
	 * <code>Set</code> that is the same as <code>this</code> except that it does
	 * not contain the specified element.
	 *
	 * @param e
	 *            the element to be removed
	 * @return a set similar to <code>this</code> but without <code>e</code>
	 */
	@Override
	public Set<E> remove(E e) {
		return this.subtract(new LinkedSet<E>(e));
	}

	/**
	 * Returns a new <code>Set</code> that is the union of <code>this
	 * Set</code> and <code>that Set</code>. This is intended to be the mathematical
	 * operation of union on sets.
	 *
	 * @param other
	 *            the other <code>Set</code> to take the union with
	 * @return a <code>Set</code> containing the union of the two input
	 *         <code>Set</code>s
	 */
	@Override
	public Set<E> union(Set<E> that) {
		Set<E> out;
		if (that.size() >= this.size()) {
			out = new LinkedSet<E>(((LinkedSet<E>) that).getHead());
			for (E element : this) {
				out = out.adjoin(element);
			}
		} else {
			out = new LinkedSet<E>(head);
			for (E element : that) {
				out = out.adjoin(element);
			}
		}

		return out;
	}

	/**
	 * Adjoin a set with a new element. If the specified element is already in the
	 * set then returns the original Set. Otherwise, returns a new Set containing
	 * all of the elements of this set and also the specified new element.
	 * 
	 * @param e
	 *            the element to be added
	 * @return a Set containing all of the elements of this set that also contains
	 *         <code>e</code>
	 */
	@Override
	public Set<E> adjoin(E e) {
		LinkedSet<E> out = new LinkedSet<E>(head);
		if (e == null) {
			if (!out.contains(null)) {
				out.head = new LinkedNode<E>(null, out.head);
			}
			return out;
		}

		boolean flag = true;
		for (E element : this) {
			if (e.equals(element))
				flag = false;
		}

		if (flag) {
			out.head = new LinkedNode<E>(e, out.head);
		}
		return out;
	}

	@Override
	public boolean contains(Object o) {
		for (E element : this) {
			if (o == null) {
				if (element == null) {
					return true;
				}
			} else if (o.equals(element))
				return true;
		}
		return false;
	}

	/**
	 * Returns true if this set is a subset (or equal to) the given set. More
	 * formally, returns true if and only if every member of 'this' set is also
	 * contained in <code>that</code> set.
	 *
	 * @param that
	 *            the set to check whether this is a subset of
	 * @return true if every member of <code>this</code> set is also a member of
	 *         <code>that</code>
	 */
	@Override
	public boolean isSubset(Set<E> that) {
		iter = this.iterator();

		for (E element : this) {
			if (!that.contains(element)) {
				return false;
			}
		}

		return true;
	}

	/**
	 * Returns true if this set is a superset (or equal to) the given set. This is
	 * equivalent to asking whether that set is a subset of this set.
	 *
	 * @param that
	 *            the set to check whether this is a superset of
	 * @return true if every member of <code>that</code> is also a member of
	 *         <code>this</code> set
	 */
	@Override
	public boolean isSuperset(Set<E> that) {
		return that.isSubset(this);
	}

	// Constructors
	public LinkedSet() {
	}

	public LinkedSet(E e) {
		this.head = new LinkedNode<E>(e, null);
	}

	private LinkedSet(LinkedNode<E> head) {
		this.head = head;
	}

	@Override
	public int size() {
		int out = 0;
		iter = iterator();

		while (iter.hasNext()) {
			iter.next();
			out++;
		}

		return out;
	}

	@Override
	public boolean isEmpty() {
		return head == null;
	}

	@Override
	public Iterator<E> iterator() {
		return new LinkedNodeIterator<E>(this.head);
	}

	// @Override
	// public boolean contains(Object o) {
	// if (isEmpty()) {
	// return false;
	// }
	//
	// iter = iterator();
	//
	// if (o == null) {
	// while (iter.hasNext()) {
	// if (iter.next() == o)
	// return true;
	// }
	// } else {
	// E temp;
	// while (iter.hasNext()) {
	// temp = iter.next();
	// if (temp != null && temp.equals(o))
	// return true;
	// }
	// }
	//
	// return false;
	// }

	/**
	 * Compares the specified object with this set for equality. Returns true if the
	 * specified object is also a set, the two sets have the same size, and every
	 * member of the specified set is contained in this set (or equivalently, every
	 * member of this set is contained in the specified set).
	 *
	 * @param o
	 *            object to be compared for equality with this set
	 * @return true if the specified object is equal to this set
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	@SuppressWarnings("unchecked")
	public boolean equals(Object o) {
		if (!(o instanceof Set)) {
			return false;
		}
		Set<E> that = (Set<E>) o;

		return this.isSubset(that) && that.isSubset(this);
	}

	@Override
	public int hashCode() {
		int result = 0;
		for (E e : this) {
			result += e.hashCode();
		}
		return result;
	}

	public String toString() {
		iter = iterator();
		String out = "";
		for (E element : this) {
			if (element == null)
				out += null + ", ";
			else
				out += element.toString() + ", ";
		}
		if (out.length() > 2) {
			out = out.substring(0, out.length() - 2);
		}
		return out;
	}

	public LinkedNode<E> getHead() {
		return head;
	}
}
