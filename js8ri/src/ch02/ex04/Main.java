package ch02.ex04;

import java.util.stream.Stream;

//Stream.of(values)の返り値の型はStream<int[]>になる。これは引数はオブジェクトかオブジェクトの配列を期待するため。
//intのストリームを得たい場合はvaluesの型をInteger[]にする。
public class Main {
	public static void main(final String[] args) {
		final int[] values = { 1, 4, 9, 16 };
		final Stream<int[]> stream = Stream.of(values);
		stream.forEach(e -> System.out.println(e));

		final Integer[] values2 = { 1, 4, 9, 16 };
		final Stream<Integer> intStream = Stream.of(values2);
		intStream.forEach(e -> System.out.println(e));
	}
}
