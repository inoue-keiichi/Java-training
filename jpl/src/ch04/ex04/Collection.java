package ch04.ex04;

import java.util.Iterator;

public interface Collection<E> extends Iterable<E> {
	// 指定された要素がこのコレクションに格納されていることを保証します(オプションの操作)。
	boolean add(E e);

	// 指定された要素のインスタンスがこのコレクションにあれば、そのインスタンスをコレクションから1つ削除します(オプションの操作)。
	boolean remove(Object obj);

	// コレクションの要素のイテレータを返します。
	Iterator<E> iterator();
}
