package ch02.ex11;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;

// スレッドセーフかどうかに関わらず、セグメントごとに恒等値(ArrayList)を作成して
// 並行に実行するため単一のArrayListに結果を収集できない。
public class Main {

	public static void main(final String[] args) {
		final List<String> list = new ArrayList<String>() {
			{
				for (int i = 0; i < 100; i++) {
					add(String.valueOf(i));
				}
			}
		};

		final List<String> result = list.stream().parallel().collect(() -> new ArrayList<String>(list.size()) {
			{
				for (int i = 0; i < list.size(); i++) {
					add(null);
				}
			}
		},
				new BiConsumer<ArrayList<String>, String>() {
					int i = 0;

					@Override
					public void accept(final ArrayList<String> t, final String u) {
						t.set(i, u);
						i++;
					}

				},
				(l1, l2) -> {
					System.out.println("Don't execute!");
				});
		System.out.println(result);

	}

}
