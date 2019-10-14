package ch03.ex07;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ColorAttrTest{
	ColorAttr colorAttrExpected = new ColorAttr("dog", "red");
	ColorAttr colorAttrActual = new ColorAttr("dog", "red");
	ColorAttr colorAttrActual2 = new ColorAttr("cat", "red");
	ColorAttr colorAttrActual3 = new ColorAttr("dog", "blue");
	
	//equals()のテスト
	@Test
	void equalsTest() {
		assertEquals(true, colorAttrExpected.equals(colorAttrExpected));
		assertEquals(true, colorAttrExpected.equals(colorAttrActual));		
		assertEquals(false, colorAttrExpected.equals(colorAttrActual2));
		assertEquals(false, colorAttrExpected.equals(colorAttrActual3));
		assertEquals(false, colorAttrExpected.equals(null));
		assertEquals(false, colorAttrExpected.equals(new Object()));
	}
	
	//hashCode()のテスト
	@Test
	void hashCodeTest() {		
		assertEquals(true, colorAttrActual.hashCode()==colorAttrExpected.hashCode());
		assertEquals(false, colorAttrActual2.hashCode()==colorAttrExpected.hashCode());
		assertEquals(false, colorAttrActual3.hashCode()==colorAttrExpected.hashCode());
	}
}
