package ch01.ex08;

import java.util.ArrayList;
import java.util.List;

// ラムダ式の外側のスコープにあっても配列の各要素やオブジェクトのフィールドを取り出せるので正当なコードである。

// 従来のforループだとiがeffectively finalな変数でないため文法的にエラーとなってコンパイルできない。
//for (int i = 0; i < names.length; i++) {
//	runners2.add(() -> System.out.println(names[i]));
//}
public class Main {
	public static void main(final String[] args) {
		final String[] names = { "Peter", "Paul", "Mary" };
		final List<Runnable> runners1 = new ArrayList<>();
		for (final String name : names) {
			runners1.add(() -> System.out.println(name));
		}
		final Thread thread1 = new Thread(() -> {
			runners1.forEach((r) -> {
				r.run();
			});
		});
		thread1.start();
	}
}
