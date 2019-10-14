package ch02.ex10;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class VehicleTest {
	@Test
	void test() {
		final Vehicle toyota = new Vehicle("Tom");
		
		assertEquals("0(Tom)", toyota.toString());
	}
}
