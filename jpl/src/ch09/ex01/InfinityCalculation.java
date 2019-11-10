package ch09.ex01;

public class InfinityCalculation {

	public static void main(String[] args) {
		// 同符号の場合
		float x = Float.POSITIVE_INFINITY;
		float y = Float.POSITIVE_INFINITY;
		System.out.println(String.format("%s%f\t%s%f", "x = ", x ,"y = ", y));
		System.out.println(String.format("%s%f", "x+y: ", x + y));
		System.out.println(String.format("%s%f", "x-y: ", x - y));
		System.out.println(String.format("%s%f", "x/y: ", x / y));
		System.out.println(String.format("%s%f", "x%y: ", x % y));
		x = Float.NEGATIVE_INFINITY;
		y = Float.NEGATIVE_INFINITY;
		System.out.println(String.format("%s%f\t%s%f", "x = ", x ,"y = ", y));
		System.out.println(String.format("%s%f", "x+y: ", x + y));
		System.out.println(String.format("%s%f", "x-y: ", x - y));
		System.out.println(String.format("%s%f", "x/y: ", x / y));
		System.out.println(String.format("%s%f", "x%y: ", x % y));
		// 異符号の場合
		x = Float.POSITIVE_INFINITY;
		y = Float.NEGATIVE_INFINITY;
		System.out.println(String.format("%s%f\t%s%f", "x = ", x ,"y = ", y));
		System.out.println(String.format("%s%f", "x+y: ", x + y));
		System.out.println(String.format("%s%f", "x-y: ", x - y));
		System.out.println(String.format("%s%f", "x/y: ", x / y));
		System.out.println(String.format("%s%f", "x%y: ", x % y));
		x = Float.NEGATIVE_INFINITY;
		y = Float.POSITIVE_INFINITY;
		System.out.println(String.format("%s%f\t%s%f", "x = ", x ,"y = ", y));
		System.out.println(String.format("%s%f", "x+y: ", x + y));
		System.out.println(String.format("%s%f", "x-y: ", x - y));
		System.out.println(String.format("%s%f", "x/y: ", x / y));
		System.out.println(String.format("%s%f", "x%y: ", x % y));
	}
}
