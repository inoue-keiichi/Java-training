package ch02.ex15;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class VehicleTest {
	@Test
	void test() {
		Vehicle vitz = new Vehicle("Taro");
		
		vitz.changeSpeed(100);
		assertEquals(100, vitz.getSpeed());
		vitz.stop();
		assertEquals(0, vitz.getSpeed());
	}
}
