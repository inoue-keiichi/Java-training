package ch02.ex03;

import static org.junit.Assert.*;

import org.junit.Test;

public class MainTest {

	@Test
	public void test() {
		final String str = "I hope you do not mind my contacting you out of red. "
				+ "My name is Yoko Ito of the financial division at ABC Corporation. "
				+ "I came across your website and found your new accounting service XXX very interesting.";

		final long actual = Main.countWords(str, 4);
		assertEquals(18, actual);
	}

	@Test
	public void test_parallel() {
		final String str = "I hope you do not mind my contacting you out of red. "
				+ "My name is Yoko Ito of the financial division at ABC Corporation. "
				+ "I came across your website and found your new accounting service XXX very interesting.";

		final long actual = Main.countWordsParallel(str, 4);
		assertEquals(18, actual);
	}
}
