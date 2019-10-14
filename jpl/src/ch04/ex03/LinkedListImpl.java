package ch04.ex03;

import java.util.Objects;

import ch03.ex08.Vehicle;

public class LinkedListImpl implements LinkedList {

	private Object obj;
	private LinkedListImpl next;
	private static final LinkedListImpl first = null;

	public LinkedListImpl(Object obj, LinkedListImpl next) {
		this.obj = obj;
		this.next = next;
	}

	public LinkedListImpl(Object obj) {
		this(obj, first);
	}

	public static void main(String[] args) {
		Vehicle car1 = new Vehicle("Akira");
		Vehicle car2 = new Vehicle("Bob");
		Vehicle car3 = new Vehicle("Cancey");
		Vehicle car4 = new Vehicle("Daigo");
		final LinkedListImpl linkedList1 = new LinkedListImpl(car1, first);
		final LinkedListImpl linkedList2 = new LinkedListImpl(car2, linkedList1);
		final LinkedListImpl linkedList3 = new LinkedListImpl(car3, linkedList2);
		final LinkedListImpl linkedList4 = new LinkedListImpl(car4, linkedList3);

		final LinkedListImpl linkedListClone = (LinkedListImpl) linkedList4.clone();
		car4.setOwner("Domadoma");
		linkedListClone.setNext(null);

		System.out.println(linkedList4);
		System.out.println(linkedListClone);
	}

	public LinkedList clone() {
		try {
			LinkedListImpl linkedList = (LinkedListImpl) super.clone();
			if (!Objects.isNull(linkedList.next)) {
				linkedList.next = (LinkedListImpl) this.next.clone();
			}
			return linkedList;
		} catch (CloneNotSupportedException e) {
			throw new InternalError(e.toString());
		}
	}

	public int count() {
		LinkedListImpl nextNode = next;
		int count = 1;

		while (nextNode != null) {
			count++;
			nextNode = nextNode.next;
		}
		return count;
	}

	public String toString() {
		String str = "[" + obj.toString();
		LinkedListImpl linkedList = this.next;
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

	public void setNext(LinkedListImpl next) {
		this.next = next;
	}
}
