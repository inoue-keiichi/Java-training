package ch08.ex13;

@TestCase(expected = 0)
@TestCase(expected = 1)
@TestCase(expected = 2)
public class Main {
	public static void main(final String[] args) throws ClassNotFoundException {
		final TestCase[] testCases = Main.class.getAnnotationsByType(TestCase.class);
		System.out.println(testCases[0].expected());

	}

}
