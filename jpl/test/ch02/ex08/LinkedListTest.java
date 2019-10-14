package ch02.ex08;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class LinkedListTest {
	@Test
	void test() {
		final LinkedList list = new LinkedList("Test", LinkedList.first);

		assertEquals("Test", list.obj);
		assertEquals(null, list.next);
	}
}
