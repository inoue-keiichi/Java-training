package ch13.ex01;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

@TestInstance(Lifecycle.PER_CLASS)
public class StringUtilTest {
	String testStr;

	@BeforeAll
	void init() {
		testStr = "abcdefghijabcabc";
	}

	@Test
	void test() {
		assertEquals(3, StringUtil.countChar(testStr, 'a'));
		assertEquals(0, StringUtil.countChar(testStr, 'k'));
		assertEquals(0, StringUtil.countChar("", 'a'));
		assertEquals(-1, StringUtil.countChar(null, 'a'));
	}
}