package ch01.ex09;

public class Main {

	public static void main(final String[] args) {
		final Collection2<Person> col = new Collection2Impl<>();
		final Main main = new Main();
		col.add(main.new Person("Akiyama", 10));
		col.add(main.new Person("Bob", 12));
		col.add(main.new Person("Cho", 42));
		col.add(main.new Person("Dago", 71));
		col.add(main.new Person("Erika", 7));
		col.forEachIf((person) -> {
			System.out.println(person.name + " is child.");
		}, (person) -> {
			return person.age < 20;
		});
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
