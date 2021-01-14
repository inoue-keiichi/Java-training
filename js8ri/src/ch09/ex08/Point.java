package ch09.ex08;

public class Point {
	private int x;
	private int y;

	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int compareTo(Point other) {
		int diff = compareTo(x, other.x);
		if (diff != 0) {
			return diff;
		}
		return compareTo(y, other.y);
	}

	private int compareTo(int x, int y) {
		return (x < y) ? -1 : ((x == y) ? 0 : 1);
	}
}
