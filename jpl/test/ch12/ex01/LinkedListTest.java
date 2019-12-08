package ch12.ex01;

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

	LinkedList<Vehicle> linkedList1;
	LinkedList<Vehicle> linkedList2;
	LinkedList<Vehicle> linkedList3;

	@BeforeAll
	void init() {
		car1 = new Vehicle("Akira");
		car2 = new Vehicle("Bob");
		car3 = new Vehicle("Cancey");
		linkedList1 = new LinkedList<Vehicle>(car1);
		linkedList2 = new LinkedList<Vehicle>(car2, linkedList1);
		linkedList3 = new LinkedList<Vehicle>(car3, linkedList2);
	}

	@Test
	void findTest() {
		LinkedList<Vehicle> actualLinkedList1 = null;
		LinkedList<Vehicle> actualLinkedList2 = null;
		LinkedList<Vehicle> actualLinkedList3 = null;

		try {
			actualLinkedList1 = linkedList3.find(car1);
			actualLinkedList2 = linkedList3.find(car2);
			actualLinkedList3 = linkedList3.find(car3);
		} catch (ObjectNotFoundException e) {
			e.getStackTrace();
		} finally {
			assertEquals(linkedList1, actualLinkedList1);
			assertEquals(linkedList2, actualLinkedList2);
			assertEquals(linkedList3, actualLinkedList3);
		}
	}

	@Test
	void findErrorTest() {
		String acutualErrorMessage = null;

		try {
			linkedList3.find(new Vehicle("Daigo"));
		} catch (ObjectNotFoundException e) {
			acutualErrorMessage = e.getMessage();
		} finally {
			assertEquals("The Object named \"3(Daigo)\" is not found", acutualErrorMessage);
		}

	}
}
