package ch03.ex10;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import ch03.ex08.Vehicle;

public class LinkedListTest {


	// リストが複製されていることをテスト
	@Test
	void cloneTest() {		
		Vehicle car1 = new Vehicle("Akira");
		Vehicle car2 = new Vehicle("Bob");
		Vehicle car3 = new Vehicle("Cancey");
		Vehicle car4 = new Vehicle("Daigo");
		LinkedList linkedList1 = new LinkedList(car1);
		LinkedList linkedList2 = new LinkedList(car2, linkedList1);
		LinkedList linkedList3 = new LinkedList(car3, linkedList2);
		LinkedList linkedList4 = new LinkedList(car4, linkedList3);
		LinkedList linkedListClone = linkedList4.clone();

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
