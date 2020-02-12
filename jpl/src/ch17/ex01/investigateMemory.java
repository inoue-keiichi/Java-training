package ch17.ex01;

import java.util.ArrayList;
import java.util.List;

public class investigateMemory {
	public static void main(String[] args) {
		final Runtime runTime = Runtime.getRuntime();
		List<Object> list = new ArrayList<>();
		// 初期のシステムメモリ
		System.out.println("System memory :" + runTime.freeMemory() + " byte");
		// メモリを使う
		for (int i = 0; i < 1000000; i++) {
			Object obj = new Object();
			list.add(obj);
		}
		System.out.println("System memory :" + runTime.freeMemory() + " byte");
		list.clear();
		list = null;
		// gc後
		runTime.gc();
		System.out.println("System memory :" + runTime.freeMemory() + " byte");
	}
}
