package ch01.ex01;

import java.util.Arrays;

// A. 同じスレッドで実行される
public class Main {

	public static void main(String[] args) {
		final String[] strs = { "Hokkaido", "Aomori", "Iwate", "Yamagata" };

		System.out.println("Call : " + Thread.currentThread().getName());
		Arrays.sort(strs, (x, y) -> {
			System.out.println("Comparator : " + Thread.currentThread().getName());
			return Integer.compare(x.length(), y.length());
		});
	}
}
