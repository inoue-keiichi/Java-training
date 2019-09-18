package src.ch02.ex06;

public class LinkedList {
	public static void main() {
		LinkedList node1 = new LinkedList();
		node1.obj = "Tom";
		node1.next = first;
		LinkedList node2 = new LinkedList();
		node2.obj = "Mike";
		node2.next = node1;
		LinkedList node3 = new LinkedList();
		node3.obj = "Mai";
		node3.next = node2;

		LinkedList nodeNext = node3;
		do {
			System.out.println(nodeNext.obj);
			nodeNext = nodeNext.next;
		} while (nodeNext != null);
	}

	public static LinkedList first = null;

	public Object obj;
	public LinkedList next;
}
