package ch03.ex16;

import java.util.function.BiConsumer;
import java.util.function.Supplier;

// second: BiConsumer<T, Throwable> だと３つ目のパラメーターは使わなくてもエラーハンドリングできる。
// しかし、secondにエラー処理も任せることになり実装が分かりにくくなるので、個人的にはsecondはRunnableで
// ３つ目のパラメーターにエラーハンドリングを任せた方が良いと思う。
public class Main {
	public static void main(final String[] args) {
		final Supplier<Integer> first = () -> {
			// Integerに変換できなかったらエラー出る
			final int r = Integer.valueOf(args[0]);
			return r;
		};
		final BiConsumer<Integer, Throwable> second = (t, u) -> {
			// スローされたエラーを処理する
			if (u != null) {
				throw new RuntimeException(u);
			}
			// tが負だったらエラー出る
			final String[] strs = new String[t];
			for (int i = 0; i < strs.length; i++) {
				strs[i] = "Hello!";
			}
			for (final String str : strs) {
				System.out.println(str);
			}

		};
		doInOrderAsync(first, second);
	}

	public static <T> void doInOrderAsync(final Supplier<T> first, final BiConsumer<T, Throwable> second) {
		final Thread t = new Thread() {
			@Override
			public void run() {
				try {
					final T result = first.get();
					second.accept(result, null);
				} catch (final Throwable t) {
					second.accept(null, t);
				}
			}
		};
		t.start();
	}
}
