package ch21.ex04;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;

import org.junit.Test;

public class ShortStringsTest {
	@Test
	public void hasNextTest() {
		List<String> list = new ArrayList<>();
		list.add("Okinawa");
		list.add("Hokkaido");
		list.add("Gifu");
		ListIterator<String> iterator = list.listIterator();
		ShortStringsListIterator shortStrings = new ShortStringsListIterator(iterator, 7);

		assertEquals(true, shortStrings.hasNext());
		assertEquals("Okinawa", shortStrings.next());
		assertEquals(true, shortStrings.hasNext());
		assertEquals("Gifu", shortStrings.next());
		assertEquals(false, shortStrings.hasNext());
	}

	@Test
	public void hasNextTest_hasAlreadyfound() {
		List<String> list = new ArrayList<>();
		list.add("Okinawa");
		list.add("Hiroshima");
		list.add("Gifu");
		ListIterator<String> iterator = list.listIterator();
		ShortStringsListIterator shortStrings = new ShortStringsListIterator(iterator, 10);

		assertEquals(true, shortStrings.hasNext());
		assertEquals(true, shortStrings.hasNext());
		assertEquals("Okinawa", shortStrings.next());
		assertEquals(true, shortStrings.hasNext());
		assertEquals(true, shortStrings.hasNext());
		assertEquals("Hiroshima", shortStrings.next());
		assertEquals(true, shortStrings.hasNext());
		assertEquals(true, shortStrings.hasNext());
		assertEquals("Gifu", shortStrings.next());
		assertEquals(false, shortStrings.hasNext());
		assertEquals(false, shortStrings.hasNext());
	}

	@Test
	public void removeTest() {
		List<String> list = new ArrayList<>();
		list.add("Okinawa");
		list.add("Gifu");
		list.add("Hokkaido");
		ListIterator<String> iterator = list.listIterator();
		ShortStringsListIterator shortStrings = new ShortStringsListIterator(iterator, 7);

		assertEquals(true, shortStrings.hasNext());
		assertEquals("Okinawa", shortStrings.next());
		assertEquals(true, shortStrings.hasNext());
		assertEquals("Gifu", shortStrings.next());
		assertEquals(false, shortStrings.hasNext());
		shortStrings.remove();
		try {
			shortStrings.next();
			fail();
		} catch (NoSuchElementException e) {

		}
	}
}
