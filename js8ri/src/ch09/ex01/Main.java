package ch09.ex01;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.Scanner;

public class Main {
	public static void main(final String[] args) {
		Scanner in = null;
		PrintWriter out = null;
		try {
			in = new Scanner(Paths.get("./src/resource/sample2.txt"));
			out = new PrintWriter("./src/resource/out.txt");
			while (in.hasNext()) {
				out.println(in.next().toLowerCase());
			}
		} catch (final IOException e) {
			e.printStackTrace();
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (final Throwable e) {
					if (out != null) {
						out.close();
					}
					throw e;
				}
			}
			if (out != null) {
				out.close();
			}
		}
	}
}
