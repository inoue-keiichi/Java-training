package ch14.ex09;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Stack;

public class ThreadLevel implements Runnable {
	final ThreadGroup group;

	public ThreadLevel(final ThreadGroup group) {
		this.group = group;
	}

	@Override
	public void run() {
		for (;;) {
			showGroupLevels(group);
			// showThreadList(group);

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
		}
	}

	/**
	 * 引数のThreadGroupの階層とその階層中のThreadを表示します。
	 * 
	 * @param group
	 */
	public void showGroupLevels(ThreadGroup group) {
		final Stack<ThreadGroup> groupStack = new Stack<>();

		do {
			groupStack.push(group);
			group = group.getParent();
		} while (!Objects.isNull(group));

		String str = "  ";
		ThreadGroup tempGroup = groupStack.pop();
		System.out.print(tempGroup.getName() + ": ");
		System.out.println(getThreadList(tempGroup).toString());

		while (!groupStack.empty()) {
			System.out.println("");
			tempGroup = groupStack.pop();
			System.out.print(str + tempGroup.getName() + ": ");
			System.out.println(getThreadList(tempGroup).toString());
			str += "  ";
		}
	}

	/**
	 * 引数のThreadGroupにあるThreadの名前のリストを返します。
	 * 
	 * @param group
	 * @return Threadの名前のリスト
	 */
	public List<String> getThreadList(final ThreadGroup group) {
		final int numThreads = group.activeCount();
		final Thread[] threadArray = new Thread[numThreads];
		group.enumerate(threadArray);

		final List<String> threadNames = new ArrayList<>();
		for (final Thread th : threadArray) {
			threadNames.add(th.getName());
		}
		return threadNames;
	}
}
