package ch08.ex10;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class Main {
	public static void main(final String[] args) throws IOException {
		Path path = null;
		try {
			path = extractZipFile(
					"/Library/Java/JavaVirtualMachines/adoptopenjdk-8.jdk/Contents/Home/src.zip");
			displayFiles(path, "transient|volatile");
		} finally {
			if (path != null) {
				deleteFile(path);
			}
		}
	}

	private static Path extractZipFile(final String dir) throws IOException {
		final Path path = Files.createTempDirectory(null);
		Files.createDirectories(path);
		try (ZipInputStream in = new ZipInputStream(new FileInputStream(
				new File("/Library/Java/JavaVirtualMachines/adoptopenjdk-8.jdk/Contents/Home/src.zip")))) {
			ZipEntry e;
			while ((e = in.getNextEntry()) != null) {
				final Path dst = Paths.get(path.toString(), e.getName());
				if (e.isDirectory()) {
					Files.createDirectories(dst);
					continue;
				}
				Files.createDirectories(dst.getParent());
				try {
					Files.createFile(dst.getFileName());
				} catch (final FileAlreadyExistsException ex) {

				}
				Files.write(dst, in.readAllBytes());
			}
		}
		return path;
	}

	private static void displayFiles(final Path path, final String pattern) throws IOException {
		Files.walk(path).filter(p -> {
			final File file = new File(p.toString());
			if (file.isDirectory()) {
				return false;
			}
			try (Scanner scanner = new Scanner(file)) {
				while (scanner.hasNext()) {
					if (scanner.next().matches(pattern)) {
						return true;
					}
				}
			} catch (final FileNotFoundException e1) {
				// TODO 自動生成された catch ブロック
				e1.printStackTrace();
			}
			return false;
		}).forEach(System.out::println);
	}

	private static void deleteFile(final Path path) throws IOException {
		Files.walk(path).forEach(p -> {
			final File file = new File(p.toString());
			file.deleteOnExit();
		});
	}
}
