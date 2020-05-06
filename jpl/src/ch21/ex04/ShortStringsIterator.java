package ch21.ex04;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ShortStringsIterator implements Iterator<String> {
	private Iterator<String> strings;
	private String nextShort;
	private final int maxLen;

	public ShortStringsIterator(final Iterator<String> strings, final int maxLen) {
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
		throw new UnsupportedOperationException();
	}

}
