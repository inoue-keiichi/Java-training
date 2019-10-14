package ch03.ex10;

import java.util.Objects;
import ch03.ex08.Vehicle;

public class LinkedList implements Cloneable {
	private Object obj;
	private LinkedList next;
	private static final LinkedList first = null;

	public LinkedList(Object obj, LinkedList next) {
		this.obj = obj;
		this.next = next;
	}

	public LinkedList(Object obj) {
		this(obj, first);
	}

	public static void main(String[] args) {
		Vehicle car1 = new Vehicle("Akira");
		Vehicle car2 = new Vehicle("Bob");
		Vehicle car3 = new Vehicle("Cancey");
		Vehicle car4 = new Vehicle("Daigo");
		final LinkedList linkedList1 = new LinkedList(car1, first);
		final LinkedList linkedList2 = new LinkedList(car2, linkedList1);
		final LinkedList linkedList3 = new LinkedList(car3, linkedList2);
		final LinkedList linkedList4 = new LinkedList(car4, linkedList3);

		final LinkedList linkedListClone = linkedList4.clone();
		car4.setOwner("Domadoma");
		linkedListClone.setNext(null);

		System.out.println(linkedList4);
		System.out.println(linkedListClone);
	}

	public LinkedList clone() {
		try {
			LinkedList linkedList = (LinkedList) super.clone();
			if (!Objects.isNull(linkedList.next)) {
				linkedList.next = this.next.clone();
			}
			return linkedList;
		} catch (CloneNotSupportedException e) {
			throw new InternalError(e.toString());
		}
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

//	public void remove() {
//		if(Objects.isNull(next)) {
//			return;
//		}
//		
//		LinkedList nextNode = next;
//		int length = count();
//		System.out.println(length);
//		for (int i = 0; i < length-2; i++) {
//			nextNode = nextNode.next;
//		}
//		nextNode.setNext(null);
//	}
	
	public void remove() {
		if(Objects.isNull(next)) {
			return;
		}
		
		LinkedList nextNode = next;
		while(!Objects.isNull(nextNode)) {
			nextNode = nextNode.next;
			if(Objects.isNull(nextNode.next.next)) {
				nextNode.next =null;
				break;
			}
		}
	}
	

	public String toString() {
		String str = "[" + obj.toString();
		LinkedList linkedList = this.next;
		while (!Objects.isNull(linkedList)) {
			str += ", ";
			str += linkedList.obj.toString();
			linkedList = linkedList.next;
		}
		str += "]";
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
