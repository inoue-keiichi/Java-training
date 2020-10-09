package ch02.ex10;

import java.util.Arrays;
import java.util.stream.Stream;

public class Main {

	public static void main(final String[] args) {
		final Stream<Double> stream = Stream.of(3.0, 5.0, 6.0, 8.0);
		final double[] array = { 2.0, 3.0, 4.0 };
		Arrays.stream(array).average();
		System.out.println(average(stream));
	}

	public static Double average(final Stream<Double> stream) {
		final int[] count = { 0 };
		return stream.reduce(Double.valueOf(0), (total, addNum) -> {
			count[0]++;
			return total + addNum;
		}) / count[0];
	}

}
