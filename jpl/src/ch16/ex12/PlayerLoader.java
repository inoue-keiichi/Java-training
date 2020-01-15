package ch16.ex12;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;
import java.net.MalformedURLException;

public class PlayerLoader extends ClassLoader {
	public Class<?> findClass(String name) throws ClassNotFoundException {
		try {
			byte[] buf = bytesForClass(name);
			return defineClass(null, buf, 0, buf.length);
		} catch (IOException e) {
			throw new ClassNotFoundException(e.toString());
		}
	}

	private byte[] bytesForClass(String name) throws IOException, ClassNotFoundException {
		FileInputStream in = null;
		try {
			in = streamFor(name + ".class");
			int length = in.available(); // バイト数を得る
			if (length == 0)
				throw new ClassNotFoundException(name);
			byte[] buf = new byte[length];
			in.read(buf);
			return buf;
		} finally {
			if (in != null)
				in.close();
		}
	}

	private FileInputStream streamFor(final String name) throws FileNotFoundException {
		return new FileInputStream(name);
	}

	public URL findResource(String name) {
		File f = fileFor(name);
		if (!f.exists()) {
			return null;
		}
		try {
			return f.toURI().toURL();
		} catch (MalformedURLException e) {
			return null; // 起きるはずがない
		}
	}

	public Enumeration<URL> findResources(String name) {
		Vector<URL> vector = new Vector<>();
		File dir = dirFor(name);
		if (!dir.exists()) {
			return null;
		}
		try {
			for (File file : dir.listFiles()) {
				vector.add(file.toURI().toURL());
			}
		} catch (MalformedURLException e) {
			return null; // 起きるはずがない
		}
		return vector.elements();
	}

	private File fileFor(final String name) {
		return new File(name);
	}

	private File dirFor(final String name) {
		return new File(name);
	}
}
