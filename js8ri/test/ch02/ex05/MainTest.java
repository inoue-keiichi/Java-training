package ch02.ex05;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Calendar;
import java.util.stream.Stream;

import org.junit.Test;

public class MainTest {
	@Test
	public void test() {
		final Stream<Long> stream = Main.getRandomStream(Calendar.getInstance().getTimeInMillis())
				.filter(num -> num >= 0L && num < 100000000000000L).limit(10000);

		final long[] actuals = distributeNum(100000000000000L, stream);
		int actualLength = 0;
		for (final long actual : actuals) {
			// 乱数の一様性の評価
			assertTrue(900 < actual && actual < 1100);
			actualLength = actualLength + (int) actual;
		}
		// 発生した乱数の長さを評価
		assertEquals(10000, actualLength);
	}

	private long[] distributeNum(final Long maxNum, final Stream<Long> nums) {
		final long[] result = new long[10];
		final long unit = maxNum / 10;
		nums.forEach(num -> {
			if (num < unit + 1) {
				result[0]++;
			} else if (num < unit * 2 + 1) {
				result[1]++;
			} else if (num < unit * 3 + 1) {
				result[2]++;
			} else if (num < unit * 4 + 1) {
				result[3]++;
			} else if (num < unit * 5 + 1) {
				result[4]++;
			} else if (num < unit * 6 + 1) {
				result[5]++;
			} else if (num < unit * 7 + 1) {
				result[6]++;
			} else if (num < unit * 8 + 1) {
				result[7]++;
			} else if (num < unit * 9 + 1) {
				result[8]++;
			} else if (num < unit * 10 + 1) {
				result[9]++;
			}
		});
		return result;
	}

}
