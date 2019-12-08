package ch13.ex02;

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
		assertEquals(3, StringUtil.countString(testStr, "abc"));
		assertEquals(3, StringUtil.countString(testStr, "a"));
		assertEquals(0, StringUtil.countString(testStr, "abd"));
		assertEquals(-1, StringUtil.countString(testStr, ""));
		assertEquals(-1, StringUtil.countString("", "a"));
		assertEquals(-1, StringUtil.countString(testStr, null));
		assertEquals(-1, StringUtil.countString(null, "a"));
	}
}