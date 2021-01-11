package ch08.ex08;

import java.nio.file.Path;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
	private static Queue tasks;

	public static void main(final String[] args) {
		final Queue<Path> queue = Collections.checkedQueue(new LinkedList<Path>(), Path.class);

		// 引数で型パラメーターを渡さない
		getMoreWork(queue);
		tasks.offer(Path.of("./", "hoge", "hoge"));
		// Stringを挿入してもコンパイルできるが、実行時にClassCastExceptionになる
		tasks.offer("./fuga/fuga");
	}

	public static void getMoreWork(final Queue queue) {
		tasks = queue;
	}
}
