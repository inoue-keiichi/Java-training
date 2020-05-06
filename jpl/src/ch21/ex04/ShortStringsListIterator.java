package ch21.ex04;

import java.util.ListIterator;
import java.util.NoSuchElementException;

public class ShortStringsListIterator implements ListIterator<String> {
	private ListIterator<String> strings;
	private String nextShort;
	private final int maxLen;

	public ShortStringsListIterator(final ListIterator<String> strings, final int maxLen) {
		this.strings = strings;
		this.maxLen = maxLen;
	}

	@Override
	public boolean hasNext() {
		if (nextShort != null) {
			return true;
		}
		while (strings.hasNext()) {
			nextShort = strings.next();
			if (nextShort.length() <= maxLen) {
				return true;
			}
		}
		nextShort = null;
		return false;
	}

	@Override
	public String next() {
		if (nextShort == null && !hasNext()) {
			throw new NoSuchElementException();
		}
		String n = nextShort;
		nextShort = null;
		return n;
	}

	@Override
	public void remove() {
		strings.remove();
	}

	@Override
	public boolean hasPrevious() {
		// TODO 自動生成されたメソッド・スタブ
		return false;
	}

	@Override
	public String previous() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public int nextIndex() {
		// TODO 自動生成されたメソッド・スタブ
		return 0;
	}

	@Override
	public int previousIndex() {
		// TODO 自動生成されたメソッド・スタブ
		return 0;
	}

	@Override
	public void set(String e) {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void add(String e) {
		// TODO 自動生成されたメソッド・スタブ

	}
}
