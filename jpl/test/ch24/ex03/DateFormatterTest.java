package ch24.ex03;

import static org.junit.Assert.*;

import java.util.Map;

import org.junit.Test;

public class DateFormatterTest {
	@Test
	public void normalTest() {
		final Map<String, String> actualResult = DateFormatter.convertDate("2020/02/02");
		assertTrue(actualResult.size() > 0);
		assertTrue(actualResult.size() < 5);

		assertEquals("2020/02/02", actualResult.get("long"));
		assertEquals("2020/02/02", actualResult.get("medium"));
		assertEquals("20/02/02", actualResult.get("short"));
	}

	@Test
	public void errorTest() {
		final String str = "2020";
		try {
			DateFormatter.convertDate(str);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(str, e.getMessage());
		}
	}
}
