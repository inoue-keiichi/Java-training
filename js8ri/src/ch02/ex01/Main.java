package ch02.ex01;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// 同時並行で実行してもカウントの加算で衝突しないようにし、計算が終わったらスレッドを正常に終了
// できるように制御しないといけないので実装がめんどくさい。
public class Main {

	public static void main(final String[] args) throws IOException {
		final String contents = new String(Files.readAllBytes(Paths.get("./src/resource/sample1.txt")),
				StandardCharsets.UTF_8);
		final List<String> words = Arrays.asList(contents.split("[\\P{L}]+"));

		System.out.println(countWords(words));
	}

	public static int countWords(final List<String> words) {
		// セグメントに分けて各セグメントごとにthreadを実行
		final List<List<String>> segments = divideList(words, 3);
		final int[] counts = new int[segments.size()];
		final List<Thread> list = new ArrayList<>();
		for (int i = 0; i < counts.length; i++) {
			final int tmp = i;
			final Thread thread = new Thread(() -> {
				for (final String w : segments.get(tmp)) {
					if (w.length() > 12) {
						counts[tmp]++;
					}
				}
			});
			list.add(thread);
			thread.start();
		}

		// threadが終了するまで待つ
		while (true) {
			for (final Thread thread : list) {
				if (thread.getState() != Thread.State.TERMINATED) {
					continue;
				}
			}
			break;
		}

		// threadの結果を合計する
		int result = 0;
		for (final int count : counts) {
			result = result + count;
		}
		return result;
	}

	private static List<List<String>> divideList(final List<String> list, final int listNum) {
		final int listSize = list.size() / listNum;
		final List<List<String>> result = new ArrayList<>();
		List<String> segment = new ArrayList<>();
		int count = 0;
		for (final String str : list) {
			segment.add(str);
			count++;
			if (count >= listSize) {
				result.add(segment);
				count = 0;
				segment = new ArrayList<>();
			}
		}
		if (segment.size() >= 1) {
			result.add(segment);
		}
		return result;
	}
}
