package test.ch02.ex03;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import src.ch02.ex03.Vehicle;

class VehicleTest {
	@Test
	void test() {
		final Vehicle toyota = new Vehicle();
		final Vehicle daihatsu = new Vehicle();
		final Vehicle nissan = new Vehicle();

		assertEquals(0, toyota.id);
		assertEquals(1, daihatsu.id);
		assertEquals(2, nissan.id);
	}
}
