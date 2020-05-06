package ch22.ex03;

import static org.junit.Assert.*;

import org.junit.Test;

public class WhichCharsTest {
	@Test
	public void normalTest() {
		WhichChars whichChars = new WhichChars("test ぴ づ ひ");
		String actualResult = whichChars.toString();
		assertTrue(actualResult.matches("[ estひづぴ]") || actualResult.matches("[ひづぴ est]"));
	}
}
