package ch09.ex06;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Main {
	public static void main(final String[] args) throws IOException {
		writeReversely(Path.of("./src/resource/sample1.txt"), Path.of("./src/resource/out.txt"));
	}

	public static void writeReversely(Path from, Path to) throws IOException {
		final List<String> list = Files.readAllLines(from);
		List<String> reverseList = new ArrayList<>() {
			{
				for (int i = list.size() - 1; i > -1; i--) {
					add(list.get(i));
				}
			}
		};
		Files.write(to, reverseList);
	}
}
