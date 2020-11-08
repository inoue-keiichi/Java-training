package ch03.ex23;

import java.util.function.Function;

public class Pair<T> {
	T t1;
	T t2;

	public Pair(final T t) {
		this.t1 = t;
		this.t2 = clone(t);
	}

	/**
	 * tと同じインスタンスを生成します。
	 *
	 * @param t Pairの片方
	 * @return
	 */
	private T clone(final T t) {
		// 実装は省略
		return null;
	}

	/**
	 * T型の対になるオブジェクトをそれぞれT->Rに変換した新しいPairを返します。
	 *
	 * @param f   T->Rへ変換する関数型インターフェース
	 * @param <R> 返り値の型パラメータ
	 * @return
	 */
	public <R> Pair<R> map(final Function<T, R> f) {
		return new Pair<R>(f.apply(t1));
	}
}