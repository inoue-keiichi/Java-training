package ch04.ex04;

import java.util.Iterator;

public interface Set<E> extends Collection<E> {
	// 指定された要素がセット内になかった場合、セットに追加します(オプションの操作)。
	boolean add(E e);

	// 指定された要素がセット内にあった場合、セットから削除します(オプションの操作)。
	boolean remove(Object o);
	
	//セット内の各要素についてのイテレータを返します。
	Iterator<E> iterator();
}
