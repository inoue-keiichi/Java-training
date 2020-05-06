package ch21.ex02;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.WeakHashMap;

public class DataHandler {
	//private File lastFile;
	//private WeakReference<byte[]> lastData;
	private WeakHashMap<File, byte[]> weakHashMap = new WeakHashMap<>();

	byte[] readFile(final File file) {
		byte[] data;

		if (this.weakHashMap.containsKey(file)) {
			data = this.weakHashMap.get(file);
			if (data != null) {
				return data;
			}
		}
		data = readBytesFromFile(file);
		this.weakHashMap.put(file, data);
		return data;
	}

	private byte[] readBytesFromFile(final File file) {
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

	final WeakHashMap<File, byte[]> getWeakHashMap() {
		return this.weakHashMap;
	}
}
