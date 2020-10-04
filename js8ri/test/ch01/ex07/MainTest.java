package ch01.ex07;

import static org.junit.Assert.*;

import org.junit.Test;

public class MainTest {
	int actual = 100;

	@Test
	public void test() {
		final Thread thread = new Thread(Main.andThen(() -> {
			try {
				Thread.sleep(2000);
			} catch (final InterruptedException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
			actual = actual + 50;
		}, () -> {
			actual = actual / 2;
		}));
		thread.start();

		while (thread.getState() != Thread.State.TERMINATED) {

		}
		assertEquals(75, actual);
	}

}
