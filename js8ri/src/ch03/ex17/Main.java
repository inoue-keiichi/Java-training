package ch03.ex17;

import java.util.function.Consumer;

public class Main {
	public static void main(final String args[]) {
		System.out.println(Thread.currentThread().getName());
		doInParallelAsync(() -> {
			Integer.valueOf("1");
		}, () -> {
			Integer.valueOf("bbb");
		}, (t) -> {
			System.out.println(Thread.currentThread().getName());
			throw new RuntimeException(t);
		});
	}

	public static void doInParallelAsync(final Runnable first, final Runnable second,
			final Consumer<Throwable> handler) {
		final Thread t1 = new Thread() {
			@Override
			public void run() {
				try {
					first.run();
				} catch (final Throwable t) {
					handler.accept(t);
				}
			}
		};
		final Thread t2 = new Thread() {
			@Override
			public void run() {
				try {
					second.run();
				} catch (final Throwable t) {
					handler.accept(t);
				}
			}
		};
		t1.start();
		t2.start();
	}
}
