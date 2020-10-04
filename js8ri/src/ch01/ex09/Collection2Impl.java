package ch01.ex09;

import java.util.Collection;
import java.util.Iterator;

public class Collection2Impl<T> implements Collection2<T> {

	@Override
	public int size() {
		return 0;
	}

	@Override
	public boolean isEmpty() {
		// TODO 自動生成されたメソッド・スタブ
		return false;
	}

	@Override
	public boolean contains(final Object o) {
		// TODO 自動生成されたメソッド・スタブ
		return false;
	}

	@Override
	public Iterator<T> iterator() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public Object[] toArray() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public <T> T[] toArray(final T[] a) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public boolean add(final T e) {
		elementData.add(e);
		return true;
	}

	@Override
	public boolean remove(final Object o) {
		// TODO 自動生成されたメソッド・スタブ
		return false;
	}

	@Override
	public boolean containsAll(final Collection<?> c) {
		// TODO 自動生成されたメソッド・スタブ
		return false;
	}

	@Override
	public boolean addAll(final Collection<? extends T> c) {
		// TODO 自動生成されたメソッド・スタブ
		return false;
	}

	@Override
	public boolean removeAll(final Collection<?> c) {
		// TODO 自動生成されたメソッド・スタブ
		return false;
	}

	@Override
	public boolean retainAll(final Collection<?> c) {
		// TODO 自動生成されたメソッド・スタブ
		return false;
	}

	@Override
	public void clear() {
		// TODO 自動生成されたメソッド・スタブ

	}

}
