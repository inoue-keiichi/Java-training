package ch20.ex08;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class RandomFile {
	public static void main(String[] args) throws FileNotFoundException, IOException {
		File file = new File("/Users/inoue-keiichi/git/Java-training/jpl/test/resources/entry.txt");
		File result = createTableFile(file);
		List<String> list = getEntryRandomly(result, file);
		for (String element : list) {
			System.out.println(element);
		}
	}

	public static File createTableFile(final File file) throws FileNotFoundException, IOException {
		final File table = new File("/Users/inoue-keiichi/git/Java-training/jpl/test/resources/table.txt");
		try (final RandomAccessFile reader = new RandomAccessFile(file, "r");
				final BufferedWriter writer = new BufferedWriter(new FileWriter(table))) {
			String line = null;
			while (true) {
				line = reader.readLine();
				if (Objects.isNull(line)) {
					break;
				} else if (Objects.equals(line, "%%")) {
					writer.write(String.valueOf(reader.getFilePointer()));
					writer.write("\n");
				}
			}
		}
		return table;
	}

	public static List<String> getEntryRandomly(final File table, final File file)
			throws FileNotFoundException, IOException {
		final List<String> lines = new ArrayList<>();
		try (BufferedReader reader = new BufferedReader(new FileReader(table))) {
			while (true) {
				String line = reader.readLine();
				if (Objects.isNull(line)) {
					break;
				}
				lines.add(line);
			}
		}

		final int random = (int) Math.ceil(Math.random() * lines.size()) - 1;
		try (final RandomAccessFile reader = new RandomAccessFile(file, "r");) {
			final List<String> strs = new ArrayList<>();
			final String pointer = lines.get(random);
			reader.seek(Long.parseLong(pointer));
			String str;
			while (true) {
				str = reader.readLine();
				if (Objects.isNull(str) || Objects.equals(str, "%%")) {
					break;
				}
				strs.add(str);
			}
			return strs;
		}
	}
}
