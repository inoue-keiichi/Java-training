package test.ch02.ex08;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import src.ch02.ex08.LinkedList;

public class LinkedListTest {
	@Test
	void test() {
		final LinkedList list = new LinkedList("Test", LinkedList.first);

		assertEquals("Test", list.obj);
		assertEquals(null, list.next);
	}
}
