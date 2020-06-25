package ch20.ex09;

import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

public class FileUtil {
	public static void main(String[] args) throws IOException {
		Map<String, String> fileInfo = getFileInfo(
				"/Users/inoue-keiichi/git/Java-training/jpl/test/resources/sample.txt");
		for (final Entry<String, String> entry : fileInfo.entrySet()) {
			System.out.println(entry.getKey() + ": " + entry.getValue());
		}
	}

	public static Map<String, String> getFileInfo(String path) throws IOException {
		final File file = new File(path);
		Map<String, String> fileInfo = new LinkedHashMap<>();
		fileInfo.put("name", file.getName());
		fileInfo.put("path", file.getPath());
		fileInfo.put("absolutePath", file.getAbsolutePath());
		fileInfo.put("canonicalPath", file.getCanonicalPath());
		fileInfo.put("parentPath", file.getParent());
		fileInfo.put("freeSpace", String.valueOf(file.getFreeSpace()) + "[bytes]");
		fileInfo.put("totalSpace", String.valueOf(file.getTotalSpace()) + "[bytes]");
		fileInfo.put("usableSpace", String.valueOf(file.getUsableSpace()) + "[bytes]");
		return fileInfo;
	}
}
