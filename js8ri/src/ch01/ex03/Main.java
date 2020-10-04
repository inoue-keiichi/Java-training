package ch01.ex03;

import java.io.File;
import java.util.Arrays;

// 拡張子の変数(extension)がエンクロージングスコープからキャプチャされる。
public class Main {

	public static void main(final String[] args) {
		final String[] files = getFiles("./test/resource/dir", null);
		System.out.println(Arrays.toString(files));

	}

	public static String[] getFiles(final String dirName, final String extension) {
		final File targetDir = new File(dirName);
		return targetDir.list((dir, name) -> {
			if (extension == null || extension.isEmpty()) {
				final File file = new File(dir, name);
				return !file.isDirectory() && name.matches("[^\\.]*");
			}
			return name.matches(".*\\." + extension);
		});
	}
}
