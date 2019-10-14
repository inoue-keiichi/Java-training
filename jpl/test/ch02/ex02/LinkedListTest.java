package ch02.ex02;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class LinkedListTest {

	@Test
	void test() {
		final LinkedList list = new LinkedList();
		list.obj = "Test";
		list.next = LinkedList.first;

		assertEquals("Test", list.obj);
		assertEquals(null, list.next);
	}
}
