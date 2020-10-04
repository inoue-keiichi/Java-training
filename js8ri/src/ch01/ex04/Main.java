package ch01.ex04;

import java.io.File;
import java.util.Arrays;

public class Main {
	public static void main(final String[] args) {
		final File dir = new File("./test/resource/dir");
		final File[] files = dir.listFiles();
		Main.sortFiles(files);
		System.out.println(Arrays.toString(files));
	}

	public static void sortFiles(final File[] files) {
		Arrays.sort(files, (file1, file2) -> {
			Boolean.valueOf(true).compareTo(false);
			final int firstSortCondition = Boolean.compare(file1.isDirectory(), file2.isDirectory());
			if (firstSortCondition != 0) {
				return firstSortCondition * (-1);
			}
			return file1.getPath().compareTo(file2.getPath());
		});
	}
}
