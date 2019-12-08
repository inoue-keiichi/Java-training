package ch13.ex06;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

@TestInstance(Lifecycle.PER_CLASS)
public class StringUtilTest {
	String testStr1;
	String testStr2;

	@BeforeAll
	void init() {
		testStr1 = "123456789";
		testStr2 = "123aaa789";
	}

	@Test
	void test() {
		String expectedResult1 = "1,23,45,67,89";
		String expectedResult2 = "The number is only permitted: 123aaa789";
		String expectedResult3 = "The index is out of String length";

		String actualResult = null;

		try {
			actualResult = StringUtil.separator(testStr1, 2);
		} catch (Exception e) {
		} finally {
			assertEquals(expectedResult1, actualResult);
		}

		try {
			actualResult = StringUtil.separator(testStr2);
		} catch (Exception e) {
			actualResult = e.getMessage();
		} finally {
			assertEquals(expectedResult2, actualResult);
		}

		try {
			actualResult = StringUtil.separator(testStr1, 9);
		} catch (Exception e) {
			actualResult = e.getMessage();
		} finally {
			assertEquals(expectedResult3, actualResult);
		}

	}
}