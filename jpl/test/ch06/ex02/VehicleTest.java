package ch06.ex02;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import ch06.ex02.Vehicle.VehicleAngle;

public class VehicleTest{
	@Test
	void test() {
		Vehicle vitz = new Vehicle();
		vitz.setAngle(0);
		
		vitz.turn(90);
		assertEquals(90, vitz.getAngle());
		vitz.turn(VehicleAngle.TURN_LEFT);
		assertEquals(180, vitz.getAngle());
		vitz.turn(VehicleAngle.TURN_RIGHT);
		assertEquals(90, vitz.getAngle());
	}
}
