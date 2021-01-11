package ch08.ex02;

public class Main {

	// intの最大値は2^31-1であり、最小値は-2^31である。
	// ゆえに最小値をnegateExactの引数にすると例外がスローされる。
	public static void main(final String[] args) {
		final int i = Math.negateExact(Integer.MIN_VALUE);
		System.out.println(i);
	}

}
