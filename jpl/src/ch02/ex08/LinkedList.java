package ch02.ex08;

public class LinkedList {
	public  static LinkedList first = null;
	
	public Object obj;
	public LinkedList next;

	public LinkedList(Object obj, LinkedList next) {
		this.obj = obj;
		this.next = next;
	}
}
