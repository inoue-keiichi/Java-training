package ch06.ex03;

import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;

//AtomicLong: 2942[ms]
//LongAdder: 248[ms]
public class Main {
	public static void main(final String[] args) {
		final AtomicLong atomicLong = new AtomicLong();
		final long atomicLongTime = measureTime(1000, () -> {
			for (int j = 0; j < 100000; j++) {
				atomicLong.incrementAndGet();
			}
		});
		System.out.println("AtomicLong: " + atomicLongTime + "[ms]");

		final LongAdder longAdder = new LongAdder();
		final long longAdderTime = measureTime(1000, () -> {
			for (int j = 0; j < 100000; j++) {
				longAdder.increment();
			}
		});
		System.out.println("LongAdder: " + longAdderTime + "[ms]");
	}

	private static long measureTime(final int threadNum, final Runnable task) {
		final Thread[] threads = createThreads(threadNum, task);
		final long first = System.currentTimeMillis();
		for (final Thread thread : threads) {
			thread.start();
		}

		// 全てのスレッドが終了するまで待つ
		boolean flag = true;
		while (flag) {
			for (int k = 0; k < threadNum; k++) {
				if (threads[k].getState() != Thread.State.TERMINATED) {
					break;
				}
				if (k == threadNum - 1) {
					flag = false;
				}
			}
		}
		return System.currentTimeMillis() - first;
	}

	private static Thread[] createThreads(final int num, final Runnable task) {
		final Thread[] threads = new Thread[num];
		for (int i = 0; i < num; i++) {
			final Thread thread = new Thread(task);
			threads[i] = thread;
		}
		return threads;
	}
}
