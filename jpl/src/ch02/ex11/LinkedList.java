package ch02.ex11;


public class LinkedList {
	public static LinkedList first = null;
	
	public Object obj;
	public LinkedList next;

	public LinkedList(Object obj, LinkedList next) {
		this.obj = obj;
		this.next = next;
	}
	
	public String toString() {
		String str = obj.toString();
		return str;
	}
}
