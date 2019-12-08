package ch12.ex01;

import java.util.Objects;

public class LinkedList<E> implements Cloneable {
	private E element;
	private LinkedList<E> next;

	public LinkedList(E element, LinkedList<E> next) {
		this.element = element;
		this.next = next;
	}

	public LinkedList(E element) {
		this(element, null);
	}

	public LinkedList<E> find(E element) throws ObjectNotFoundException {
		if (this.element == element) {
			return this;
		}

		LinkedList<E> nextNode = next;

		while (nextNode != null) {
			if (nextNode.element.equals(element)) {
				return nextNode;
			}
			nextNode = nextNode.next;
		}
		throw new ObjectNotFoundException(element.toString());
	}

	public int count() {
		LinkedList<E> nextNode = next;
		int count = 1;

		while (nextNode != null) {
			count++;
			nextNode = nextNode.next;
		}
		return count;
	}

	public String toString() {
		String str = "[" + element.toString();
		LinkedList<E> linkedList = this.next;
		while (!Objects.isNull(linkedList)) {
			str += ", ";
			str += linkedList.element.toString();
			linkedList = linkedList.next;
		}
		str += "]";
		return str;
	}

	public LinkedList<E> clone() {
		try {
			LinkedList<E> linkedList = (LinkedList<E>) super.clone();
			if (!Objects.isNull(linkedList.next)) {
				linkedList.next = this.next.clone();
			}
			return linkedList;
		} catch (CloneNotSupportedException e) {
			throw new InternalError(e.toString());
		}
	}

	public E getElement() {
		return this.element;
	}

	public LinkedList<E> getNext() {
		return this.next;
	}

	public void setElement(E element) {
		this.element = element;
	}

	public void setNext(LinkedList<E> next) {
		this.next = next;
	}
}
