package ch08.ex03;

public class Main {

	public static int gcdOperand(final int a, final int b) {
		if (b == 0) {
			return a;
		} else if (a == 0) {
			return b;
		}

		int x = Math.max(a, b);
		int y = Math.min(a, b);
		int remainder;
		while (true) {
			remainder = x % y;
			if (remainder == 0) {
				return Math.abs(y);
			}
			x = y;
			y = remainder;
		}
	}

	public static int gcdFloorMod(final int a, final int b) {
		if (b == 0) {
			return a;
		} else if (a == 0) {
			return b;
		}

		int x = Math.max(a, b);
		int y = Math.min(a, b);
		int remainder;
		while (true) {
			remainder = Math.floorMod(x, y);
			if (remainder == 0) {
				return Math.abs(y);
			}
			x = y;
			y = remainder;
		}
	}

	public static int gcdRem(final int a, final int b) {
		if (b == 0) {
			return a;
		} else if (a == 0) {
			return b;
		}

		// 絶対値が大きい方をxとする。
		int x;
		int y;
		if (Math.abs(a) > Math.abs(b)) {
			x = a;
			y = b;
		} else {
			x = b;
			y = a;
		}
		if (rem(x, y) == 0) {
			return y;
		}
		return gcdRem(y, rem(x, y));
	}

	private static int rem(final int a, final int b) {
		if ((a > 0 && b > 0) || (a < 0 && b < 0)) {
			return Math.abs(a) % Math.abs(b);
		}

		int i = 1;
		int r = a + b * i;
		while (r > Math.abs(b) || r < 0) {
			i++;
			r = a + b * i;

		}
		return r;
	}
}
