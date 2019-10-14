package ch02.ex09;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class VehicleTest {

	@Test
	void test() {
		final Vehicle toyota = new Vehicle("Tom");
		final Vehicle daihatsu = new Vehicle("Mike");
		final Vehicle nissan = new Vehicle("Mai");
		
		assertEquals(2, Vehicle.getMaxId());
	}

}
