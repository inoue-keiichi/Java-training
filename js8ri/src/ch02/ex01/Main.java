package ch02.ex01;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;

// 同時並行で実行してもカウントの加算で衝突しないようにし、計算が終わったらスレッドを正常に終了
// できるように制御しないといけないので実装がめんどくさい。
public class Main {

	public static void main(final String[] args) throws IOException {
		final String contents = new String(Files.readAllBytes(Paths.get("./src/resource/sample1.txt")),
				StandardCharsets.UTF_8);
		final List<String> words = Arrays.asList(contents.split("[\\P{L}]+"));

		System.out.println(countWords(words));
	}

	public static int countWords(final List<String> words) {
		final Object obj = new Object();
		final Queue<Runnable> tasks = new ArrayDeque<>();
		final int[] count = { 0 };
		for (final String w : words) {
			tasks.add(() -> {
				if (w.length() > 12) {
					synchronized (obj) {
						count[0]++;
					}
				}
			});
		}
		tasks.add(RunnableImpl.STOP);
		tasks.add(RunnableImpl.STOP);
		tasks.add(RunnableImpl.STOP);
		final RunnableImpl run1 = new RunnableImpl(tasks.poll());
		final RunnableImpl run2 = new RunnableImpl(tasks.poll());
		final RunnableImpl run3 = new RunnableImpl(tasks.poll());
		final Thread thread1 = new Thread(run1);
		final Thread thread2 = new Thread(run2);
		final Thread thread3 = new Thread(run3);
		thread1.start();
		thread2.start();
		thread3.start();
		while (!(thread1.getState() == Thread.State.TERMINATED && thread2.getState() == Thread.State.TERMINATED
				&& thread3.getState() == Thread.State.TERMINATED)) {
			if (thread1.getState() == Thread.State.WAITING) {
				run1.setTask(tasks.poll());
				synchronized (run1) {
					run1.notifyAll();
				}
			}
			if (thread2.getState() == Thread.State.WAITING) {
				run2.setTask(tasks.poll());
				synchronized (run2) {
					run2.notifyAll();
				}
			}
			if (thread3.getState() == Thread.State.WAITING) {
				run3.setTask(tasks.poll());
				synchronized (run3) {
					run3.notifyAll();
				}
			}
		}
		return count[0];
	}
}

class RunnableImpl implements Runnable {
	final static Runnable STOP = new Runnable() {
		@Override
		public void run() {
		}
	};
	Runnable task;

	public RunnableImpl(final Runnable task) {
		this.task = task;
	}

	public void setTask(final Runnable task) {
		this.task = task;
	}

	@Override
	public void run() {
		while (task != STOP) {
			task.run();
			try {
				synchronized (this) {
					this.wait();
				}
			} catch (final InterruptedException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
		}
	}

}
