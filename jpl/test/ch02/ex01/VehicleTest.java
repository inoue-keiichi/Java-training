package ch02.ex01;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class VehicleTest {
	@Test
	void test() {
		final Vehicle toyota = new Vehicle();
		toyota.owner = "Tom";
		toyota.angle = 90;
		toyota.speed = 100;

		assertEquals("Tom", toyota.owner);
		assertEquals(90, toyota.angle);
		assertEquals(100, toyota.speed);
	}

}
