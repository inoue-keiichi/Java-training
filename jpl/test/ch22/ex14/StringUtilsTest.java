package ch22.ex14;

import static org.junit.Assert.*;

import org.junit.Test;

public class StringUtilsTest {
	@Test
	public void normalTest() {
		final String str = "10.0 12.5 21.8 12 18";
		final double expectedResult = 74.3;
		assertTrue(expectedResult == StringUtils.sum(str));
	}

	@Test
	public void normalTest_emptyParam() {
		final String str = "";
		final double expectedResult = 0;
		assertTrue(expectedResult == StringUtils.sum(str));
	}

	@Test
	public void normalTest_space() {
		final String str = "   ";
		final double expectedResult = 0;
		assertTrue(expectedResult == StringUtils.sum(str));
	}

	@Test
	public void errorTest_invalidParam() {
		final String str = "10.0 12.5 21.8 12 18 hoge";
		try {
			StringUtils.sum(str);
			fail();
		} catch (Exception e) {
			assertEquals("For input string: \"hoge\"", e.getMessage());
		}

	}
}
