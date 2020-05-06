package ch20.ex05;

import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.Objects;

public class FindString {
	public static void main(String[] args) throws IOException {
		if (args.length != 2) {
			throw new IllegalArgumentException("need String and file");
		}
		final String targetStr = args[0];
		FileReader fileIn = new FileReader(args[1]);
		boolean isFound = false;
		LineNumberReader in = null;
		try {
			in = new LineNumberReader(fileIn);
			String str;
			System.out.println("The target is '" + targetStr + "'");
			while (Objects.nonNull(str = in.readLine())) {
				if (str.matches("(.*)" + targetStr + "(.*)")) {
					isFound = true;
					System.out.println(in.getLineNumber() + ": " + str);
				}
			}
			if (!isFound) {
				System.out.println("'" + targetStr + "' is not found");
			}
		} finally {
			if (Objects.isNull(in)) {
				return;
			}
			in.close();
		}
	}
}
