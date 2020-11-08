package ch03.ex17;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class MainTest {
	@Test
	public void test_fistThrowError() {
		final int[] actual = { 0 };
		Main.doInParallelAsync(() -> {
			Integer.valueOf("aaa");
		}, () -> {
			Integer.valueOf("3");
		}, (t) -> {
			actual[0] = actual[0] + 1;
		});
		try {
			Thread.sleep(3000);
		} catch (final InterruptedException e) {
			e.printStackTrace();
		}
		assertEquals(1, actual[0]);
	}

	@Test
	public void test_secondThrowError() {
		final int[] actual = { 0 };
		Main.doInParallelAsync(() -> {
			Integer.valueOf("3");
		}, () -> {
			Integer.valueOf("bbb");
		}, (t) -> {
			actual[0] = actual[0] + 1;
		});
		try {
			Thread.sleep(3000);
		} catch (final InterruptedException e) {
			e.printStackTrace();
		}
		assertEquals(1, actual[0]);
	}
}
