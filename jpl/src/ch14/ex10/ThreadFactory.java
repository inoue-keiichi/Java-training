package ch14.ex10;

import java.util.Objects;

public class ThreadFactory {
	public final Object runLock = new Object();
	public final Object addLock = new Object();
	private final Runnable runnable;
	public Runnable inputRunnable;
	private final Thread thread;
	public boolean stopFlg;
	public int i = 0;

	public ThreadFactory() {
		runnable = new Runnable() {
			@Override
			public void run() {
				while (true) {
					synchronized (runLock) {
						try {
							while (inputRunnable == null) {
								runLock.wait();
								inputRunnable = ThreadPool.getTask();
								if (stopFlg) {
									return;
								}
							}
						} catch (InterruptedException e) {
							// TODO 自動生成された catch ブロック
							e.printStackTrace();
						}
					}

					if (Objects.nonNull(inputRunnable)) {
						inputRunnable.run();
						inputRunnable = null;
					}
					synchronized (addLock) {
						addLock.notifyAll();
					}
					if (stopFlg) {// dispatchが全て終わったらstopflg変える
						return;
					}
				}
			}
		};
		stopFlg = false;
		thread = new Thread(runnable);
	}

	public final void setRunnable(Runnable inputRunnable) {
		this.inputRunnable = inputRunnable;
	}

	public final Thread getThread() {
		return thread;
	}
}
