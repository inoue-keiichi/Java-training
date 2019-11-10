package ch10.ex05;

public class BetweenChars {
	/**
	 * 引数に渡した２つの文字とその間の文字を表示します。
	 * 
	 * @param a 文字
	 * @param b 文字
	 */
	public static void showBetweenChars(char a, char b) {
		if (b < a) {
			char temp = b;
			b = a;
			a = temp;
		}

		final int len = b - a + 1;
		final char[] chars = new char[len];
		for (int i = 0; i < len; i++) {
			chars[i] = (char) (a + i);
		}
		System.out.print('[');
		for (int i = 0; i < chars.length; i++) {
			System.out.print(chars[i]);
			if (i < chars.length - 1) {
				System.out.print(", ");
			}
		}
		System.out.print(']');
	}

	public static void main(String[] args) {
		showBetweenChars('\'', 'v');
	}
}
