package ch02.ex10;

import static org.junit.Assert.*;

import java.util.stream.Stream;

import org.junit.Test;

public class MainTest {

	@Test
	public void test() {
		final Double[] array = { 1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0 };
		final Stream<Double> stream = Stream.of(array);
		final Double actual = Main.average(stream);
		assertEquals(Double.valueOf(4.5), actual);
	}
}
