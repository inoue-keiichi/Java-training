package ch02.ex09;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.stream.Stream;

import org.junit.Test;

public class MainTest {

	@Test
	public void test() {
		final ArrayList<String> list1 = new ArrayList<String>() {
			{
				this.add("Hokkaido");
				this.add("Iwate");
				this.add("Akita");
			}
		};
		final ArrayList<String> list2 = new ArrayList<String>() {
			{
				this.add("Tokyo");
				this.add("Tochigi");
				this.add("Chiba");
				this.add("Saitama");
			}
		};
		final ArrayList<String> list3 = new ArrayList<String>() {
			{
				this.add("Gifu");
				this.add("Aichi");
				this.add("Shizuoka");
				this.add("Shiga");
			}
		};
		final Stream<ArrayList<String>> stream = Stream.of(list1, list2, list3);
		final ArrayList<String> actual = Main.combineWith(stream);
		assertThat(actual, hasItems("Hokkaido", "Iwate", "Akita", "Tokyo", "Tochigi", "Chiba", "Saitama", "Gifu",
				"Aichi", "Shizuoka", "Shiga"));
		assertEquals(11, actual.size());
	}

	@Test
	public void test2() {
		final ArrayList<String> list1 = new ArrayList<String>() {
			{
				this.add("Hokkaido");
				this.add("Iwate");
				this.add("Akita");
			}
		};
		final ArrayList<String> list2 = new ArrayList<String>() {
			{
				this.add("Tokyo");
				this.add("Tochigi");
				this.add("Chiba");
				this.add("Saitama");
			}
		};
		final ArrayList<String> list3 = new ArrayList<String>() {
			{
				this.add("Gifu");
				this.add("Aichi");
				this.add("Shizuoka");
				this.add("Shiga");
			}
		};
		final Stream<ArrayList<String>> stream = Stream.of(list1, list2, list3);
		final ArrayList<String> actual = Main.combineWith2(stream);
		assertThat(actual, hasItems("Hokkaido", "Iwate", "Akita", "Tokyo", "Tochigi", "Chiba", "Saitama", "Gifu",
				"Aichi", "Shizuoka", "Shiga"));
		assertEquals(11, actual.size());
	}

	@Test
	public void test3() {
		final ArrayList<String> list1 = new ArrayList<String>() {
			{
				this.add("Hokkaido");
				this.add("Iwate");
				this.add("Akita");
			}
		};
		final ArrayList<String> list2 = new ArrayList<String>() {
			{
				this.add("Tokyo");
				this.add("Tochigi");
				this.add("Chiba");
				this.add("Saitama");
			}
		};
		final ArrayList<String> list3 = new ArrayList<String>() {
			{
				this.add("Gifu");
				this.add("Aichi");
				this.add("Shizuoka");
				this.add("Shiga");
			}
		};
		final Stream<ArrayList<String>> stream = Stream.of(list1, list2, list3);
		final ArrayList<String> actual = Main.combineWith3(stream);
		assertThat(actual, hasItems("Hokkaido", "Iwate", "Akita", "Tokyo", "Tochigi", "Chiba", "Saitama", "Gifu",
				"Aichi", "Shizuoka", "Shiga"));
		assertEquals(11, actual.size());
	}
}
