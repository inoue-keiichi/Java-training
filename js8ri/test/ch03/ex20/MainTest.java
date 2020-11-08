package ch03.ex20;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class MainTest {
	@Test
	public void test() {
		final List<String> list = new ArrayList<String>() {
			{
				add("Washi");
				add("Tonbi");
				add("Hato");
				add("Suzume");
				add("Kitsutsuki");
			}
		};
		final List<Integer> actual = Main.map(list, (e) -> e.length());
		final List<Integer> expected = new ArrayList<Integer>() {
			{
				add(5);
				add(5);
				add(4);
				add(6);
				add(10);
			}
		};
		assertEquals(expected, actual);
	}
}
