package ch06.ex04;

import java.util.concurrent.atomic.LongAccumulator;

public class LongAccumulatorWrapper {
	final LongAccumulator longAccmulator = new LongAccumulator((x, y) -> Math.max(x, y), 0);

	public long calculateMax(final long num) {
		longAccmulator.accumulate(num);
		return longAccmulator.get();
	}
}
