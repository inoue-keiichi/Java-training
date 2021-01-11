package ch08.ex07;

import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Stream;

public class Main {
	public static void main(final String[] args) {
		final Person[] persons1 = { new Person("Ando", "Michel", "Taro"), new Person("Ando", "Hanako"),
				new Person("Ando", "Mickey", "Yoshihiko") };
		final Person[] persons2 = persons1.clone();

		// reversed()使う
		Arrays.sort(persons1,
				Comparator.comparing(Person::getMiddleName,
						Comparator.nullsFirst(Comparator.naturalOrder())).reversed());
		Stream.of(persons1).forEach(System.out::println);

		System.out.println("-------------------");

		// reversed()使わない
		Arrays.sort(persons2,
				Comparator.comparing(Person::getMiddleName,
						Comparator.nullsLast(Comparator.reverseOrder())));
		Stream.of(persons2).forEach(System.out::println);
	}

}

class Person {
	private final String firstName;
	private String middleName;
	private final String lastName;

	public Person(final String firstName, final String middleName, final String lastName) {
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
	}

	public Person(final String firstName, final String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public String getLastName() {
		return lastName;
	}

	@Override
	public String toString() {
		return firstName + " " + middleName + " " + lastName;

	}
}
