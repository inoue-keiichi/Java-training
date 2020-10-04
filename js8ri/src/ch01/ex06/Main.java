package ch01.ex06;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.Reader;

//「問について」
// Callableメソッドを使う場合は、accept()の返り値の型がVoidなので返り値がnullでなければならないから。

//public static void main(final String[] args) {
//	new Thread(uncheck(() -> {
//		try (Reader reader = new BufferedReader(new FileReader("hogehoge"))) {
//		}
//		return null;
//	})).start();
//}
//
//public static Runnable uncheck(final Callable<Void> callable) {
//	return () -> {
//		try {
//			callable.call();
//		} catch (final Exception e) {
//			throw new RuntimeException(e);
//		}
//	};
//}

public class Main {

	public static void main(final String[] args) {
		new Thread(uncheck(() -> {
			try (Reader reader = new BufferedReader(new FileReader("hogehoge"))) {
			}
		})).start();
	}

	public static Runnable uncheck(final RunnableEx runnableEx) {
		return () -> {
			try {
				runnableEx.accept();
			} catch (final Exception e) {
				throw new RuntimeException(e);
			}
		};
	}

	@FunctionalInterface
	public interface RunnableEx {
		void accept() throws Exception;
	}
}
