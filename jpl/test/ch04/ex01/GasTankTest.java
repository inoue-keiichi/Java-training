package ch04.ex01;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class GasTankTest{
	@Test
	void test() {		
		assertEquals(false, new GasTank(100).empty());
		assertEquals(true, new GasTank(0).empty());
	}
}