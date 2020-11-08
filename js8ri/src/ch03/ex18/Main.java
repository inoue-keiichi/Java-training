package ch03.ex18;

import java.util.function.Function;

public class Main {
	public static void main(final String args[]) {
		final CallableFunction<String, String> f = (str) -> {
			if (str.length() > 4) {
				throw new Exception("arg's length is too long!" + str);
			}
			return str + "_finished";
		};
		try {
			System.out.println(unchecked(f).apply("Hello"));
		} catch (final RuntimeException e) {
			System.out.println(e.getCause().getMessage());
		} catch (final Throwable t) {
			t.printStackTrace();
		}
	}

	public static <U, R> Function<U, R> unchecked(final CallableFunction<U, R> f) {
		return (u) -> {
			try {
				return f.call(u);
			} catch (final Exception e) {
				throw new RuntimeException(e);
			} catch (final Throwable t) {
				throw t;
			}
		};
	}
}
