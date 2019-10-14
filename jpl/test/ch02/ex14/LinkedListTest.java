package ch02.ex14;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import ch02.ex13.*;

public class LinkedListTest {
	@Test
	void test() {
		LinkedList node = new LinkedList();
		Vehicle vitz = new Vehicle("Taro");
		node.setObj(vitz);
		node.setNext(LinkedList.getFirst());
		
		assertEquals(vitz, node.getObj());
		assertEquals(null, node.getNext());
	}
}
