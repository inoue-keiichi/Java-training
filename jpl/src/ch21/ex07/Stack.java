package ch21.ex07;

import java.util.ArrayList;
import java.util.Objects;

public class Stack<E> {
	private final ArrayList<E> list;

	public Stack() {
		this.list = new ArrayList<E>();
	}

	public E push(E item) {
		this.list.add(item);
		return item;
	}

	public E pop() {
		final E element = this.list.get(this.list.size() - 1);
		this.list.remove(this.list.size() - 1);
		return element;
	}

	public E peek() {
		return this.list.get(this.list.size() - 1);
	}

	public boolean empty() {
		for (E element : this.list) {
			if (Objects.nonNull(element)) {
				return false;
			}
		}
		return true;
	}

	public int search(final Object obj) {
		if (!this.list.contains(obj)) {
			return -1;
		}
		for (int i = this.list.size() - 1; i >= 0; i--) {
			if (Objects.equals(obj, this.list.get(i))) {
				return this.list.size() - 1 - i;
			}
		}
		return -1;
	}
}
