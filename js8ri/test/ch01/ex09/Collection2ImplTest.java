package ch01.ex09;

import static org.junit.Assert.*;

import org.junit.Test;

public class Collection2ImplTest {
	@Test
	public void test() {
		final Collection2<Person> col = new Collection2Impl<>();
		col.add(new Person("aaa", 10));
		col.add(new Person("bbb", 12));
		col.add(new Person("ccc", 42));
		col.add(new Person("ddd", 71));
		col.add(new Person("eee", 7));
		final int[] actual = { 0 };
		col.forEachIf((person) -> {
			actual[0]++;
		}, (person) -> {
			return person.age < 20;
		});

		assertEquals(3, actual[0]);
	}

	class Person {
		public String name;
		public int age;

		public Person(final String name, final int age) {
			this.name = name;
			this.age = age;
		}
	}
}
