package ch02.ex09;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.stream.Stream;

import org.junit.Test;

public class MainTest {

	@Test
	public void test() {
		final ArrayList<String> list1 = new ArrayList<String>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			{
				this.add("Hokkaido");
				this.add("Iwate");
				this.add("Akita");
			}
		};
		final ArrayList<String> list2 = new ArrayList<String>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			{
				this.add("Tokyo");
				this.add("Tochigi");
				this.add("Chiba");
				this.add("Saitama");
			}
		};
		final ArrayList<String> list3 = new ArrayList<String>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			{
				this.add("Gifu");
				this.add("Aichi");
				this.add("Shizuoka");
				this.add("Shiga");
			}
		};
		final Stream<ArrayList<String>> stream = Stream.of(list1, list2, list3);
		final ArrayList<String> actual = Main.combineWith(stream);
		assertThat(actual).contains("Hokkaido", "Iwate", "Akita", "Tokyo", "Tochigi", "Chiba", "Saitama", "Gifu",
				"Aichi", "Shizuoka", "Shiga");
		assertEquals(11, actual.size());
	}

	@Test
	public void test2() {
		final ArrayList<String> list1 = new ArrayList<String>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			{
				this.add("Hokkaido");
				this.add("Iwate");
				this.add("Akita");
			}
		};
		final ArrayList<String> list2 = new ArrayList<String>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			{
				this.add("Tokyo");
				this.add("Tochigi");
				this.add("Chiba");
				this.add("Saitama");
			}
		};
		final ArrayList<String> list3 = new ArrayList<String>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			{
				this.add("Gifu");
				this.add("Aichi");
				this.add("Shizuoka");
				this.add("Shiga");
			}
		};
		final Stream<ArrayList<String>> stream = Stream.of(list1, list2, list3);
		final ArrayList<String> actual = Main.combineWith2(stream);
		assertThat(actual).contains("Hokkaido", "Iwate", "Akita", "Tokyo", "Tochigi", "Chiba", "Saitama", "Gifu",
				"Aichi", "Shizuoka", "Shiga");
		assertEquals(11, actual.size());
	}

	@Test
	public void test3() {
		final ArrayList<String> list1 = new ArrayList<String>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			{
				this.add("Hokkaido");
				this.add("Iwate");
				this.add("Akita");
			}
		};
		final ArrayList<String> list2 = new ArrayList<String>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			{
				this.add("Tokyo");
				this.add("Tochigi");
				this.add("Chiba");
				this.add("Saitama");
			}
		};
		final ArrayList<String> list3 = new ArrayList<String>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			{
				this.add("Gifu");
				this.add("Aichi");
				this.add("Shizuoka");
				this.add("Shiga");
			}
		};
		final Stream<ArrayList<String>> stream = Stream.of(list1, list2, list3);
		final ArrayList<String> actual = Main.combineWith3(stream);
		assertThat(actual).contains("Hokkaido", "Iwate", "Akita", "Tokyo", "Tochigi", "Chiba", "Saitama", "Gifu",
				"Aichi", "Shizuoka", "Shiga");
		assertEquals(11, actual.size());
	}
}
