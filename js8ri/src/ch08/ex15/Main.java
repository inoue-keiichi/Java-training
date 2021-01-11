package ch08.ex15;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class Main {
	public static void main(final String[] args) throws IOException {
		findLines("./test/resource/text", "red").entrySet()
				.forEach(entry -> entry.getValue().forEach(e -> {
					System.out.println(entry.getKey() + ": " + e);
				}));
	}

	public static Map<String, List<String>> findLines(final String dir, final String pattern) throws IOException {
		final Map<String, List<String>> result = new HashMap<>();
		// ディレクトリ下の全てのファイルを捜査する
		Files.walk(Path.of(dir)).filter(path -> isFile(path)).forEach(path -> {
			try {
				Files.lines(path).filter(Pattern.compile(pattern).asPredicate()).forEach((e) -> {
					if (result.get(path.toString()) == null) {
						result.put(path.toString(), new ArrayList<>());
					}
					result.get(path.toString()).add(e);
				});
			} catch (final IOException e1) {
				// TODO 自動生成された catch ブロック
				e1.printStackTrace();
			}
		});
		return result;
	}

	private static boolean isFile(final Path path) {
		return new File(path.toString()).isFile();
	}
}
