package src.ch02.ex16;

public class LinkedList {
	private Object obj;
	private LinkedList next;
	private static final LinkedList first = null;

	public LinkedList(Object obj, LinkedList next) {
		this.obj = obj;
		this.next = next;
	}

	public int count() {
		LinkedList nextNode = next;
		int count = 1;
		
		while (nextNode != null) {
			count++;
			nextNode = nextNode.next;
		}
		return count;
	}

	public String toString() {
		String str = obj.toString();
		return str;
	}
	
	public static LinkedList getFirst() {
		return first;
	}
	
	public Object getObj() {
		return obj;
	}

	public LinkedList getNext() {
		return next;
	}

	public void setObj(Object obj) {
		this.obj = obj;
	}

	public void setNext(LinkedList next) {
		this.next = next;
	}
}
