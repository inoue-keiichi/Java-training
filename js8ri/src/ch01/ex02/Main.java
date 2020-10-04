package ch01.ex02;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
	public static void main(final String[] args) {
		final File[] files = getSubDirectories("./test/resource/dir");
		System.out.println(Arrays.toString(files));
	}

	public static File[] getSubDirectories(final String name) {
		final List<File> results = new ArrayList<>();
		final File dir = new File(name);
		final File[] belowDirs = dir.listFiles((f) -> {
			return f.isDirectory();
		});
		List<File> belowDirList = Arrays.asList(belowDirs);

		while (true) {
			final List<File> tempBelowDirList = new ArrayList<>();
			belowDirList.forEach(file -> {
				final File[] list = file.listFiles(f -> {
					return f.isDirectory();
				});
				tempBelowDirList.addAll(Arrays.asList(list));
			});
			results.addAll(belowDirList);
			if (tempBelowDirList.size() < 1) {
				break;
			}
			belowDirList = tempBelowDirList;
		}
		final File[] array = new File[results.size()];
		return results.toArray(array);
	}

	public static File[] getSubDirectories2(final String name) {
		final List<File> results = new ArrayList<>();
		final File dir = new File(name);
		final File[] belowDirs = dir.listFiles(File::isDirectory);
		List<File> belowDirList = Arrays.asList(belowDirs);

		while (true) {
			final List<File> tempBelowDirList = new ArrayList<>();
			belowDirList.forEach(file -> {
				final File[] list = file.listFiles(File::isDirectory);
				tempBelowDirList.addAll(Arrays.asList(list));
			});
			results.addAll(belowDirList);
			if (tempBelowDirList.size() < 1) {
				break;
			}
			belowDirList = tempBelowDirList;
		}
		final File[] array = new File[results.size()];
		return results.toArray(array);
	}
}
