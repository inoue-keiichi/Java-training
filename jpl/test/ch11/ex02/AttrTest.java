package ch11.ex02;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import ch03.ex08.Vehicle;

public class AttrTest {
	@Test
	void toStringTest() {
		Vehicle vehicle = new Vehicle("Akira");
		Attr<Vehicle> attrValue = new Attr<Vehicle>("vits", vehicle);
		assertEquals("vits" + " ='" + "0(Akira)" + "'", attrValue.toString());
		
		Attr<String> attrStr = new Attr<String>("name", "value");
		assertEquals("name" + " ='" + "value" + "'", attrStr.toString());
	}

}
