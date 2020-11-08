package ch03.ex02;

import static org.junit.Assert.*;

import java.util.concurrent.locks.ReentrantLock;

import org.junit.Test;

public class MainTest {

	@Test
	public void test() {
		final ReentrantLock mylock = new ReentrantLock();
		final int[] actual = { 0 };

		final Thread thread = new Thread(() -> {
			Main.withLock(mylock, () -> {
				for (int i = 0; i < 1000; i++) {
					try {
						Thread.sleep(1);
					} catch (final InterruptedException e) {
						// TODO 自動生成された catch ブロック
						e.printStackTrace();
					}
					actual[0] = actual[0] + 1;
				}
			});
		});
		thread.start();
		//先にThreadを実行してlockを獲得する
		try {
			Thread.sleep(10);
		} catch (final InterruptedException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		mylock.lock();
		try {
			actual[0] = actual[0] * 10;
		} finally {
			mylock.unlock();
		}
		assertEquals(10000, actual[0]);
	}
}
