package ch03.ex09;

import java.lang.reflect.Field;
import java.util.Comparator;

public class Main<T> {

	@SuppressWarnings("unchecked")
	public static <T> Comparator<T> lexicographicComparator(final String... fieldNames) {
		return (final T o1, final T o2) -> {
			for (final String fieldName : fieldNames) {
				try {
					final Object obj1 = getField(o1, fieldName);
					final Object obj2 = getField(o2, fieldName);
					if (obj1.equals(obj2)) {
						continue;
					}
					return ((Comparable<T>) obj1).compareTo((T) obj2);
				} catch (NoSuchFieldException | SecurityException | IllegalAccessException e) {
					e.printStackTrace();
				}
			}
			return 0;
		};
	}

	private static Object getField(final Object obj, final String name)
			throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		final Field field = obj.getClass().getDeclaredField(name);
		field.setAccessible(true);
		return field.get(obj);
	}
}
