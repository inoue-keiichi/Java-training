package test.ch02.ex16;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import src.ch02.ex15.Vehicle;
import src.ch02.ex16.LinkedList;

public class LinkedListTest {
	@Test
	void test() {
		Vehicle vitz = new Vehicle("Taro");
		LinkedList node1 = new LinkedList(vitz, LinkedList.getFirst());
		Vehicle sorio = new Vehicle("Kintaro");
		LinkedList node2 = new LinkedList(sorio, node1);
		Vehicle note = new Vehicle("Momotaro");
		LinkedList node3 = new LinkedList(note, node2);
		
		node3.count();
				
		assertEquals(3, node3.count());
	}
}