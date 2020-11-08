package ch03.ex09;

import java.lang.reflect.Field;
import java.util.Comparator;

public class Main<T> {

	public static <T> Comparator<T> lexicographicComparator(final String... fieldNames) {
		return (final T o1, final T o2) -> {
			for (final String fieldName : fieldNames) {
				try {
					final Object obj1 = getField(o1, fieldName);
					final Object obj2 = getField(o2, fieldName);
					if (obj1.equals(obj2)) {
						continue;
					}
					if (obj1 instanceof String && obj2 instanceof String) {
						return compare((String) obj1, (String) obj2);

					} else if (obj1 instanceof Number && obj2 instanceof Number) {
						return compare((Number) obj1, (Number) obj2);
					} else if (obj1 instanceof Character && obj2 instanceof Character) {
						return compare((Character) obj1, (Character) obj2);
					} else {
						throw new IllegalArgumentException();
					}
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

	private static int compare(final String str1, final String str2) {
		final char[] chr1 = str1.toCharArray();
		final char[] chr2 = str2.toCharArray();
		for (int i = 0; i < chr1.length && i < chr2.length; i++) {
			if (chr1[i] != chr2[i]) {
				return (chr1[i] - chr2[i]);
			}
		}
		return chr1.length - chr2.length;
	}

	private static int compare(final Number num1, final Number num2) {
		return num1.doubleValue() - num2.doubleValue() == 0 ? 0 : num1.doubleValue() - num2.doubleValue() < 0 ? -1 : 1;
	}

	private static int compare(final Character chr1, final Character chr2) {
		return chr1.charValue() - chr2.charValue();
	}

}
