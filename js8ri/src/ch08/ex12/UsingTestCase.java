package ch08.ex12;

@TestCase(expected = 10)
public class UsingTestCase {

	public static void main(final String[] args) {
		System.out.println(test());
	}

	public static int test() {
		final TestCase testCase = UsingTestCase.class.getAnnotation(TestCase.class);
		return testCase.expected();
	}
}
