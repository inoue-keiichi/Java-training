package test.ch02.ex02;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import src.ch02.ex02.*;

class LinkedListTest {

	@Test
	void test() {
		final Object obj = new Object();
		final LinkedList next = new LinkedList();
		final LinkedList list = new LinkedList(obj, next);

		assertEquals(obj, list.obj);
		assertEquals(next, list.next);
	}
}
