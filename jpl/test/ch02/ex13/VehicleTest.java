package test.ch02.ex13;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import src.ch02.ex13.*;

public class VehicleTest {
	@Test
	void test() {
		Vehicle vitz = new Vehicle();
		vitz.setOwner("Taro");
		vitz.setAngle(90);
		vitz.setSpeed(100);
		
		assertEquals(0, vitz.getId());
		assertEquals("Taro", vitz.getOwner());
		assertEquals(90, vitz.getAngle());
		assertEquals(100, vitz.getSpeed());
	}
}
