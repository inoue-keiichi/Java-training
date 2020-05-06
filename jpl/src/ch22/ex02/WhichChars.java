package ch22.ex02;

import java.util.HashSet;
import java.util.Iterator;

public class WhichChars {
	private HashSet<Character> used = new HashSet<>();

	public WhichChars(String str) {
		for (int i = 0; i < str.length(); i++) {
			used.add(str.charAt(i));
		}
	}

	public String toString() {
		String desc = "[";

		Iterator<Character> iterator = used.iterator();
		while (iterator.hasNext()) {
			desc += iterator.next();
		}
		return desc + "]";

	}

	public static void main(String[] args) {
		WhichChars whichChars = new WhichChars("Testing 1 2 3");
		System.out.println(whichChars.toString());
	}
}
