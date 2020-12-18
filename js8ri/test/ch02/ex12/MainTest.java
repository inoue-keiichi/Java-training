package ch02.ex12;

import static org.junit.Assert.assertArrayEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class MainTest {

	@Test
	public void test() {
		final List<String> list = new ArrayList<String>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			{
				for (int i = 0; i < 15; i++) {
					String str = "";
					for (int j = 0; j < i; j++) {
						str = str + "a";

					}
					for (int k = 0; k < 10; k++) {
						this.add(str);
					}
				}
			}
		};
		final int[] actual = Main.countShorterWordsThan(15, list.stream());
		final int[] expected = { 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10 };
		assertArrayEquals(expected, actual);
	}
}
