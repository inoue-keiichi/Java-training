package ch13.ex05;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

@TestInstance(Lifecycle.PER_CLASS)
public class StringUtilTest {
	String testStr;
	String testStr2;

	@BeforeAll
	void init() {
		testStr = "123456789";
		testStr2 = "123aaa789";
	}

	@Test
	void test() {
		String expectedResult1 = "123,456,789";
		String expectedResult2 = "The number is only permitted: 123aaa789";

		String actualResult1 = null;
		String actualResult2 = null;

		try {
			actualResult1 = StringUtil.separator(testStr);
			actualResult2 = StringUtil.separator(testStr2);
		} catch (Exception e) {
			actualResult2 = e.getMessage();
		} finally {
			assertEquals(expectedResult1, actualResult1);
			assertEquals(expectedResult2, actualResult2);
		}

	}
}