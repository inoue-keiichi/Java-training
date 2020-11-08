package ch03.ex09;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class MainTest {
	private final Person inoue = new Person(28, "Inoue", 30.8, 'k');
	private final Person sato = new Person(20, "Sato", 30.5, 'a');
	private final Person akiyama = new Person(20, "Akiyama", 30.3, 'd');
	private final Person inoue2 = new Person(28, "Inoue", 30.3, 'y');
	private final Person inoue3 = new Person(28, "Inoue", 30.8, 't');
	private List<Person> list;

	@Before
	public void setup() {
		list = new ArrayList<Person>() {
			{
				add(inoue3);
				add(inoue);
				add(sato);
				add(inoue2);
				add(akiyama);
			}
		};

	}

	@Test
	public void test_normal() {
		list.sort(Main.lexicographicComparator("age", "name", "money", "initial"));

		final List<Person> expected = new ArrayList<Person>() {
			{
				add(akiyama);
				add(sato);
				add(inoue2);
				add(inoue);
				add(inoue3);
			}
		};

		assertEquals(expected, list);
	}

	@Test
	public void test_error_fieldIsObject() {
		final List<Person> list = new ArrayList<Person>() {
			{
				add(new Person("inoue", new Object()));
				add(new Person("uemura", new Object()));
			}
		};

		try {
			list.sort(Main.lexicographicComparator("hobby", "name"));
			fail();
		} catch (final IllegalArgumentException e) {

		}
	}

	@Test
	public void test_emptyArg() {
		list.sort(Main.lexicographicComparator());

		final List<Person> expected = new ArrayList<Person>() {
			{
				add(inoue3);
				add(inoue);
				add(sato);
				add(inoue2);
				add(akiyama);
			}
		};

		assertEquals(expected, list);
	}

	@Test
	public void test_emptyList() {
		final List<Person> list = new ArrayList<>();
		list.sort(Main.lexicographicComparator());

		assertEquals(list, list);
	}

	class Person {
		private final int age;
		private final String name;
		private final double money;
		private final char initial;
		private final Object hobby;

		public Person(final int age, final String name, final double money, final char initial) {
			this.age = age;
			this.name = name;
			this.money = money;
			this.initial = initial;
			this.hobby = null;
		}

		public Person(final String name, final Object hobby) {
			this.age = 0;
			this.name = name;
			this.money = 0;
			this.initial = 'a';
			this.hobby = hobby;
		}

		@Override
		public String toString() {
			return name;
		}

		@Override
		public boolean equals(final Object obj) {
			if (this == obj) {
				return true;
			}
			if (!(obj instanceof Person)) {
				return false;
			}
			final Person p = (Person) obj;
			if (this.age != p.age) {
				return false;
			}
			if (this.name != p.name) {
				return false;
			}
			if (this.money != p.money) {
				return false;
			}
			if (this.initial != p.initial) {
				return false;
			}
			if (this.hobby != p.hobby) {
				return false;
			}
			return true;
		}
	}
}
