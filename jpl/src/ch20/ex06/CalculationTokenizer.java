package ch20.ex06;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StreamTokenizer;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;

public class CalculationTokenizer {
	public static void main(String[] args) {
		Map<String, Double> map = cal(System.in);
		for (Entry<String, Double> entry : map.entrySet()) {
			System.out.println(entry.getKey() + " = " + entry.getValue());
		}
	}

	public static Map<String, Double> cal(InputStream source) {
		final Map<String, Double> map = new HashMap<>();
		String name = null;
		Character op = null;

		try (Reader reader = new InputStreamReader(source);) {
			final StreamTokenizer in = new StreamTokenizer(reader);
			while (true) {
				in.nextToken();
				if (in.ttype == StreamTokenizer.TT_WORD && Objects.equals(in.sval, "q")) {
					break;
				} else if (Objects.isNull(name) && Objects.isNull(op) && in.ttype == StreamTokenizer.TT_WORD) {
					name = in.sval;
				} else if (Objects.nonNull(name) && Objects.isNull(op) && in.ttype == '+' || in.ttype == '-'
						|| in.ttype == '=') {
					op = (char) in.ttype;
				} else if (Objects.nonNull(name) && in.ttype == StreamTokenizer.TT_NUMBER
						&& Objects.equals(op, '+')) {
					if (map.containsKey(name)) {
						map.put(name, map.get(name) + in.nval);
					} else {
						map.put(name, in.nval);
					}
					name = null;
					op = null;
				} else if (Objects.nonNull(name) && in.ttype == StreamTokenizer.TT_NUMBER && Objects.equals(op, '-')) {
					if (map.containsKey(name)) {
						map.put(name, map.get(name) - in.nval);
					} else {
						map.put(name, -in.nval);
					}
					name = null;
					op = null;
				} else if (Objects.nonNull(name) && in.ttype == StreamTokenizer.TT_NUMBER && Objects.equals(op, '=')) {
					map.put(name, in.nval);
					name = null;
					op = null;
				} else {
					throw new IllegalArgumentException();
				}
			}
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

		return map;
	}
}
