package ch06.ex01;

import java.util.concurrent.atomic.AtomicReference;

public class Main {
	final AtomicReference<String> longest = new AtomicReference<>("");

	public String update(final String str) {
		return longest.accumulateAndGet(str, (newVal, oldVal) -> {
			return newVal.length() >= oldVal.length() ? newVal : oldVal;
		});
	}
}
