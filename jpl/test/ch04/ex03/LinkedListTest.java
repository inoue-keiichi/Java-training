package ch04.ex03;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import ch03.ex08.Vehicle;

public class LinkedListTest {

	@Test
	void cloneTest() {
		Vehicle car1 = new Vehicle("Akira");
		Vehicle car2 = new Vehicle("Bob");
		Vehicle car3 = new Vehicle("Cancey");
		Vehicle car4 = new Vehicle("Daigo");
		final LinkedListImpl linkedList1 = new LinkedListImpl(car1);
		final LinkedListImpl linkedList2 = new LinkedListImpl(car2, linkedList1);
		final LinkedListImpl linkedList3 = new LinkedListImpl(car3, linkedList2);
		final LinkedListImpl linkedList4 = new LinkedListImpl(car4, linkedList3);
		LinkedListImpl linkedListClone = (LinkedListImpl) linkedList4.clone();

		car1.setOwner("change");
		car2.setOwner("change");
		car3.setOwner("change");
		car4.setOwner("change");
		assertEquals("[3(change), 2(change), 1(change), 0(change)]", linkedListClone.toString());
		assertEquals("[3(change), 2(change), 1(change), 0(change)]", linkedList4.toString());

		linkedListClone.setNext(null);
		assertEquals("[3(change), 2(change), 1(change), 0(change)]", linkedList4.toString());
		assertEquals("[3(change)]", linkedListClone.toString());
	}
}
