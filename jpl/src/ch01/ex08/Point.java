package ch01.ex08;

public class Point {
	public double x;
	public double y;

	public void setPoint(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public static void main(String[] args) {
		Point point = new Point();
		point.setPoint(10.0, 12.3);

		System.out.println("(" + point.x + ", " + point.y + ")");
	}
}
