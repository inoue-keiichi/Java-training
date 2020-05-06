package ch22.ex06;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

public class Gaussian {
	public static void main(String[] args) {
		final Map<Double, Integer> map = Gaussian.createTable(1000000);
		Gaussian.displayGraph(map, 1000);
	}

	/**
	 *グラフを描画する。yの値は*の個数で表す。
	 *
	 * @param map
	 * 		(x, y)の組
	 * @param unit
	 * 		グラフでyの値を表す*の単位
	 */
	public static void displayGraph(Map<Double, Integer> map, final int unit) {
		List<Double> removeKeys = new ArrayList<>();
		for (Entry<Double, Integer> entry : map.entrySet()) {
			if (entry.getValue() < unit) {
				removeKeys.add(entry.getKey());
			}
		}
		for (Double key : removeKeys) {
			map.remove(key);
		}

		Double[] doubles = new Double[map.size()];
		Double[] keys = map.keySet().toArray(doubles);
		Arrays.sort(keys);
		for (double key : keys) {
			System.out.print(String.format("%10s", String.valueOf(key)));
			System.out.print(" | ");
			for (int i = 1; i < map.get(key); i++) {
				if (i % unit == 0) {
					System.out.print("*");
				}
			}
			System.out.println();
		}
	}

	/**
	 *ガウス分布に基づいてx, yの値の組をMapで返す。xは小数点１桁の精度で返す。
	 *
	 * @param trialNum
	 * 		試行回数
	 * @return
	 */
	public static Map<Double, Integer> createTable(final int trialNum) {
		final Random random = new Random();
		final Map<Double, Integer> map = new HashMap<>();
		double key;
		for (int i = 0; i < trialNum; i++) {
			key = Math.ceil(random.nextGaussian() * 10) / 10;
			if (map.containsKey(key)) {
				map.put(key, map.get(key) + 1);
			} else {
				map.put(key, 1);
			}
		}
		return map;
	}
}
