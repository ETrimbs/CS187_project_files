import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class DequeTest {

	Deque<String> deque;

	@Before
	public void setup() {
		deque = new LinkedDeque<String>();
	}

	@Test
	public void testFrontMethods() {
		assertTrue(deque.isEmpty()); // originally the deque should be empty
		deque.addToFront("Bob"); // add Bob to the front of the deque
		assertFalse(deque.isEmpty()); // deque should no longer be empty
		assertEquals("Bob", deque.first());
		deque.addToFront("VIP Alice"); // add VIP Alice to the front of the deque
		assertEquals("VIP Alice", deque.first());
		assertEquals("VIP Alice", deque.removeFront());
		assertFalse(deque.isEmpty());
		assertEquals("Bob", deque.first()); // Bob should still be in the deque
		deque.removeFront();
		assertTrue(deque.isEmpty());
	}

	@Test
	public void testRearMethods() {
		Deque<Integer> deque2 = new LinkedDeque<Integer>();

		deque2.addToRear(2);
		assertFalse(deque2.isEmpty());

		deque2.addToFront(3);
		deque2.addToFront(4);
		deque2.addToFront(5);
		deque2.addToRear(2);
		deque2.addToRear(1);

		String test = "" + deque2.removeFront() + deque2.removeFront() + deque2.removeRear() + deque2.removeRear()
				+ deque2.removeRear() + deque2.last() + deque2.removeRear();

		assertTrue(deque2.isEmpty());
		assertEquals("5412233", test);
	}
}
