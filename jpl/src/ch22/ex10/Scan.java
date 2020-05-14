package ch22.ex10;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Scan {
	public static List<String> breakUpSource(final Readable source) {
		final Scanner in = new Scanner(source);
		String str;
		in.useDelimiter("\\\\n|\\n|#.*\\\\n|#.*\\n");
		final List<String> list = new ArrayList<>();
		while (in.hasNext()) {
			str = in.next();
			if (Objects.equals(str, "")) {
				continue;
			}
			list.add(str);
		}
		return list;
	}

	public static void main(String[] args) throws FileNotFoundException {
		final FileReader fr = new FileReader("/Users/inoue-keiichi/git/Java-training/jpl/src/ch22/ex10/sample.txt");
		List<String> list = Scan.breakUpSource(fr);
		for (String str : list) {
			System.out.println(str);
		}
	}

}
