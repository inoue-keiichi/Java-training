package ch06.ex07;

import java.util.concurrent.ConcurrentHashMap;

public class Main {
	public static String searchKeyHavingMaxValue(final ConcurrentHashMap<String, Long> map) {
		return map.reduceEntries(1, (x, y) -> {
			return x.getValue() >= y.getValue() ? x : y;
		}).getKey();
	}
}
