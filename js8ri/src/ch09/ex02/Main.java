package ch09.ex02;

import java.io.Closeable;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Main {

	public static void main(final String[] args) throws Throwable {
		//意図的にエラーを起こさせるためにScannerをコンポジションした。
		SubScanner in = null;
		PrintWriter out = null;

		Throwable tryEx = null;

		try {
			in = new SubScanner(Paths.get("./src/resource/sample2.txt"));
			out = new PrintWriter("./src/resource/out.txt");
			while (in.hasNext()) {
				out.println(in.next().toLowerCase());
			}
		} catch (final Throwable e) {
			tryEx = e;
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (final Throwable e) {
					if (out != null) {
						out.close();
					}
					if (tryEx != null) {
						throw tryEx;
					}
					throw e;
				}
			}
			if (out != null) {
				try {
					out.close();
				} catch (final Throwable e) {
					if (tryEx != null) {
						throw tryEx;
					}
					throw e;
				}
			}
			if (tryEx != null) {
				throw tryEx;
			}
		}
	}
}

class SubScanner implements Closeable {
	Scanner scanner;

	public SubScanner(final Path path) throws IOException {
		this.scanner = new Scanner(path);
	}

	public boolean hasNext() {
		return scanner.hasNext();
	}

	public String next() {
		throw new IllegalArgumentException();
		//return scanner.next();
	}

	@Override
	public void close() throws IOException {
		throw new IOException();
		//scanner.close();
	}

}
