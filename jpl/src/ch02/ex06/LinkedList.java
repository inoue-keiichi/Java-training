package src.ch02.ex06;

import src.ch02.ex03.Vehicle;

public class LinkedList {
	public static void main() {
		final Vehicle toyota = new Vehicle();
		toyota.owner = "Tom";
		final Vehicle daihatsu = new Vehicle();
		daihatsu.owner = "Mike";
		final Vehicle nissan = new Vehicle();
		nissan.owner = "Mai";
		
		addLinkedList(toyota);
		addLinkedList(daihatsu);
		addLinkedList(nissan);
	}

	public Object obj;
	public LinkedList next;
	public static LinkedList first;

	public static void addLinkedList(Vehicle vehicle) {
		LinkedList node = new LinkedList();
		node.obj = vehicle;
		node.next = first;
		first = node;
	}
}
