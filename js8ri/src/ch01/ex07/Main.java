package ch01.ex07;

public class Main {
	public static void main(final String[] args) {
		new Thread(Main.andThen(() -> {
			try {
				Thread.sleep(3000);
			} catch (final InterruptedException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
			System.out.println("First");
		}, () -> {
			System.out.println("Second");
		})).start();
	}

	public static Runnable andThen(final Runnable run1, final Runnable run2) {
		return () -> {
			run1.run();
			run2.run();
		};
	}
}
