package ch04.ex01;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class BatteryTest{
	@Test
	void test() {		
		assertEquals(false, new Battery(100).empty());
		assertEquals(true, new Battery(0).empty());
	}
}