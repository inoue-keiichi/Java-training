package ch11.ex01;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.FixMethodOrder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.runners.MethodSorters;

import ch02.ex15.Vehicle;

@TestInstance(Lifecycle.PER_CLASS)
public class LinkedListTest {
	Vehicle car1;
	Vehicle car2;
	Vehicle car3;
	Vehicle car4;
	
	LinkedList<Vehicle> linkedList1;
	LinkedList<Vehicle> linkedList2;
	LinkedList<Vehicle> linkedList3;
	LinkedList<Vehicle> linkedList4;
	
	@BeforeAll
	void init() {
		car1 = new Vehicle("Akira");
		car2 = new Vehicle("Bob");
		car3 = new Vehicle("Cancey");
		car4 = new Vehicle("Daigo");
		linkedList1 = new LinkedList<Vehicle>(car1);
		linkedList2 = new LinkedList<Vehicle>(car2, linkedList1);
		linkedList3 = new LinkedList<Vehicle>(car3, linkedList2);
		linkedList4 = new LinkedList<Vehicle>(car4, linkedList3);

	}

	@Test
	void countTest() {
		linkedList4.count();
		assertEquals(4, linkedList4.count());
	}

	@Test
	void toStringTest() {
		assertEquals("[3(Daigo), 2(Cancey), 1(Bob), 0(Akira)]", linkedList4.toString());
	}

	@Test
	void cloneTest() {
		LinkedList<Vehicle> linkedListClone = linkedList4.clone();

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