package ch20.ex10;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StreamTokenizer;
import java.util.HashMap;
import java.util.Map;

public class FileUtil {
	public static Map<String, Integer> countTerms(File file) throws IOException {
		final Map<String, Integer> termMap = new HashMap<>();
		try (final Reader reader = new FileReader(file);) {
			final StreamTokenizer in = new StreamTokenizer(reader);
			// 数値や記号も文字として字句解析したい
			in.resetSyntax();
			in.wordChars('!', '~');
			while (true) {
				in.nextToken();
				if (in.ttype == StreamTokenizer.TT_WORD) {
					if (!termMap.containsKey(in.sval)) {
						termMap.put(in.sval, 1);
					} else {
						int val = termMap.get(in.sval);
						termMap.put(in.sval, ++val);
					}
				} else if (in.ttype == StreamTokenizer.TT_NUMBER) {
					final String key = String.valueOf(in.nval);
					if (!termMap.containsKey(key)) {
						termMap.put(key, 1);
					} else {
						int val = termMap.get(key);
						termMap.put(key, ++val);
					}
				} else if (in.ttype == StreamTokenizer.TT_EOF) {
					break;
				}
			}
		}
		return termMap;
	}
}
