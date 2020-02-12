package ch17.ex02;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.ref.*;
import java.util.Objects;

public class DataHandler {
	private WeakReference<File> lastFile;
	private WeakReference<byte[]> lastData;

	public byte[] readFile(File file) {
		byte[] data;

		// データを記憶しているか調べる
		if (!Objects.isNull(lastFile) && file.equals(lastFile.get())) {
			data = lastData.get();
			if (data != null)
				return data;
		}

		// 記憶していないので、読み込む
		data = readBytesFromFile(file);
		lastFile = new WeakReference<File>(file);
		lastData = new WeakReference<byte[]>(data);
		return data;
	}

	private byte[] readBytesFromFile(File file) {
		try (FileInputStream inputStream = new FileInputStream(file);
				ByteArrayOutputStream bout = new ByteArrayOutputStream();) {
			byte[] buffer = new byte[1024];
			int len = 0;
			while ((len = inputStream.read(buffer)) != -1) {
				bout.write(buffer, 0, len);
			}
			return bout.toByteArray();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	final WeakReference<File> getLastFile() {
		return lastFile;
	}

	public static void main(String args[]) {
		File file = new File("/Users/user/git/Java-training/jpl/bin/ch17/ex02/sample.class");
		final DataHandler dataHandler = new DataHandler();
		dataHandler.readFile(file);
		file = null;
		System.gc();
		if (Objects.isNull(dataHandler.lastFile.get())) {
			System.out.println("GCされました。");
		} else {
			System.out.println(dataHandler.lastFile.get());
		}
	}
}
