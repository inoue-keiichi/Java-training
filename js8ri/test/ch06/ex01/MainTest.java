package ch06.ex01;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class MainTest {

	@Test
	public void test() {
		// 複数回実行してもテスト結果が変わらないことを確かめたい
		for (int j = 0; j < 100; j++) {
			final Main main = new Main();

			final Thread thread1 = new Thread(() -> {
				String str = "";
				for (int i = 0; i < 18; i++) {
					str = str + "a";
					main.update(str);
				}
			});
			final Thread thread2 = new Thread(() -> {
				String str = "";
				for (int i = 0; i < 19; i++) {
					str = str + "b";
					main.update(str);
				}
			});
			final Thread thread3 = new Thread(() -> {
				String str = "";
				for (int i = 0; i < 20; i++) {
					str = str + "c";
					main.update(str);
				}
			});
			thread1.start();
			thread2.start();
			thread3.start();

			while (!(thread1.getState() == Thread.State.TERMINATED && thread2.getState() == Thread.State.TERMINATED
					&& thread3.getState() == Thread.State.TERMINATED)) {

			}

			assertEquals("cccccccccccccccccccc", main.longest.get());
		}
	}
}
