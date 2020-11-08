package ch03.ex02;

import java.util.concurrent.locks.ReentrantLock;

public class Main {
	public static void main(final String[] args) {
		withLock(new ReentrantLock(), () -> {
			System.out.println("Hello");
			System.out.println("World");
		});
	}

	public static void withLock(final ReentrantLock lock, final Runnable task) {
		lock.lock();
		try {
			task.run();
		} finally {
			lock.unlock();
		}
	}
}
