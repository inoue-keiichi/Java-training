package ch03.ex20;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class Main {

	/**
	 * listの各要素eに対してf(e)を実行することで、List<T> -> List<U>へ変換する。
	 *
	 * @param list
	 * @param f
	 * @param <T>
	 * @param <U>
	 * @return
	 */
	public static <T, U> List<U> map(final List<T> list, final Function<T, U> f) {
		final List<U> result = new ArrayList<>(list.size());
		list.forEach(e -> {
			result.add(f.apply(e));
		});
		return result;
	}
}
