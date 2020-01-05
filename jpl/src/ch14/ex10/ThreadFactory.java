package ch14.ex10;

import java.util.Objects;

public class ThreadFactory {
	private final Runnable runnable;
	private Runnable inputRunnable;
	private Thread thread;

	public ThreadFactory() {
		runnable = new Runnable() {
			@Override
			public void run() {
				if (Objects.nonNull(inputRunnable)) {
					inputRunnable.run();
				}
				// 初期化
				inputRunnable = null;
				thread = new Thread(runnable);
			}
		};
		thread = new Thread(runnable);
	}

	public final void setRunnable(Runnable inputRunnable) {
		this.inputRunnable = inputRunnable;
	}

	public final Thread getThread() {
		return thread;
	}
}
