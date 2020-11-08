package ch03.ex18;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

public class MainTest {
	@Test
	public void test() {
		final CallableFunction<String, String> f = (str) -> {
			if (str.length() > 4) {
				throw new Exception("arg of length is too long!" + str);
			}
			return str + "_finished";
		};
		Main.unchecked(f).apply("aaa");
	}

	@Test
	public void test_catchError() {
		final CallableFunction<String, String> f = (str) -> {
			if (str.length() > 4) {
				throw new Exception("arg of length is too long!: " + str);
			}
			return str + "_finished";
		};
		try {
			Main.unchecked(f).apply("about");
			fail();
		} catch (final RuntimeException e) {
			assertEquals("arg of length is too long!: about", e.getCause().getMessage());
		}
	}
}
