package ch06.ex05;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class SignalTest {
	@Test
	void test() {
		assertEquals("RED", Signal.valueOf("RED").getColor().getName());
		assertEquals("YELLOW", Signal.valueOf("YELLOW").getColor().getName());
		assertEquals("BLUE", Signal.valueOf("BLUE").getColor().getName());
	}
}