package ch02.ex11;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import ch02.ex10.Vehicle;

public class LinkedListTest {
	
	@Test
	void test() {
		final Vehicle toyota = new Vehicle("Tom");
		final LinkedList node = new LinkedList(toyota, LinkedList.first);

		assertEquals("0(Tom)", node.toString());
	}

}
