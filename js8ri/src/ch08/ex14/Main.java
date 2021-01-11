package ch08.ex14;

import java.util.Locale;
import java.util.Objects;

public class Main {
	String hoge;

	// Nullチェックが簡潔にかけるので可読性が上がる。
	// 動的にエラーメッセージを変更したい場合に有効。例えばロケールごとに言語を変えてメッセージを書くとか。
	public Main(final String hoge, final Locale locale) {
		this.hoge = Objects.requireNonNull(hoge, () -> {
			if (locale.equals(Locale.JAPAN)) {
				return "hogeはnullではダメです.";
			} else {
				return "hoge must not be null.";
			}

		});
	}
}
