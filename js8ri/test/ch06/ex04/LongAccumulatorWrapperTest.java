package ch06.ex04;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class LongAccumulatorWrapperTest {

	@Test
	public void test() {
		final LongAccumulatorWrapper target = new LongAccumulatorWrapper();
		long actual = target.calculateMax(-1);
		assertEquals(0, actual);
		actual = target.calculateMax(5);
		assertEquals(5, actual);
	}
}
