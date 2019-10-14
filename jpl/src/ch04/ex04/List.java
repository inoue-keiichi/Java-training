package ch04.ex04;

import java.util.Iterator;
import java.util.ListIterator;

public interface List<E> extends Collection<E> {
	// 指定された要素をこのリストの最後に追加します(オプションの操作)。
	boolean add(E e);

	// 指定された要素がこのリストにあれば、その最初のものをリストから削除します(オプションの操作)。
	boolean remove(Object obj);

	// このリスト内の要素を適切な順序で反復するイテレータを返します。
	Iterator<E> iterator();

	// このリスト内の要素を(適切な順序で)反復するリスト・イテレータを返します。
	ListIterator<E> listIterator();

	// このリスト内の指定された位置にある要素を返します。
	E get(int index);

	// このリストに要素がない場合にtrueを返します。
	boolean isEmpty();

}
