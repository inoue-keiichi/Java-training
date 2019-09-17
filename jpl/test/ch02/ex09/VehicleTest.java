package test.ch02.ex09;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import src.ch02.ex09.Vehicle;

class VehicleTest {

	@Test
	void test() {
		final Vehicle toyota = new Vehicle("Tom");
		final Vehicle daihatsu = new Vehicle("Mike");
		final Vehicle nissan = new Vehicle("Mai");
		
		assertEquals(2, Vehicle.getMaxId());
	}

}
