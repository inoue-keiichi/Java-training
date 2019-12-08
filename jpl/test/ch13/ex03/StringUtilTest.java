package ch13.ex03;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

@TestInstance(Lifecycle.PER_CLASS)
public class StringUtilTest {
	String testStr1;
	String testStr2;
	String testStr3;

	@BeforeAll
	void init() {
		testStr1 = "shinbunshi";
		testStr2 = "ihsnubnihs";
		testStr3 = "abcdefg";
	}

	@Test
	void test() {
		final String[] expectedResult1 = { "shinbunsh", "sh", "sh" };
		final String[] expectedResult2 = { "snubnih" };
		final String[] expectedResult3 = { "abcdefg" };
		assertArrayEquals(expectedResult1, StringUtil.delimitedString(testStr1, 's', 'h'));
		assertArrayEquals(expectedResult2, StringUtil.delimitedString(testStr2, 's', 'h'));
		assertArrayEquals(expectedResult3, StringUtil.delimitedString(testStr3, 'a', 'z'));
		assertArrayEquals(null, StringUtil.delimitedString(testStr1, 'x', 's'));
		assertArrayEquals(null, StringUtil.delimitedString(testStr3, 'g', 'c'));
	}
}