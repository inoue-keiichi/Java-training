package ch22.ex09;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;

public class Scan {
	private static int CELLS = 10;

	/**
	 * csvファイルを読み込む。
	 * @param source
	 * @param regex
	 * @return
	 * @throws IOException
	 */
	public static List<String[]> readCSVTable(final Readable source, final String regex) throws IOException {
		Scanner in = new Scanner(source);
		List<String[]> vals = new ArrayList<>();

		Pattern pat = Pattern.compile(regex, Pattern.MULTILINE);
		while (in.hasNextLine()) {
			String line = in.findInLine(pat);
			if (line != null) {
				String[] cells = new String[CELLS];
				MatchResult match = in.match();
				for (int i = 0; i < CELLS; i++) {
					cells[i] = match.group(i + 1);
					// csvファイルの1行のcell数が引数より多いとエラー
					if (cells[i].indexOf(',') != -1) {
						throw new IOException("too many kanma");
					}
				}
				vals.add(cells);
				in.nextLine(); // 改行読み飛ばし
				continue;
			}
			// 空行は無視する
			line = in.nextLine();
			if (Objects.equals(line, "")) {
				continue;
			}
			throw new IOException("input format error");
		}
		if (Objects.nonNull(in)) {
			in.close();
		}

		IOException ex = in.ioException();
		if (ex != null) {
			throw ex;
		}
		return vals;
	}

	public static void main(String[] args) throws IOException {
		long start;
		long end;
		final String regex1 = "^(.*),(.*),(.*),(.*),(.*),(.*),(.*),(.*),(.*),(.*)";
		final String regex2 = "^([^,]*),([^,]*),([^,]*),([^,]*),([^,]*),([^,]*),([^,]*),([^,]*),([^,]*),([^,]*)";
		final String regex3 = "^(.+),(.+),(.+),(.+),(.+),(.+),(.+),(.+),(.+),(.+)";
		final String regex4 = "^(.+?),(.+?),(.+?),(.+?),(.+?),(.+?),(.+?),(.+?),(.+?),(.+?)";
		final FileReader fr1 = new FileReader("/Users/inoue-keiichi/git/Java-training/jpl/src/ch22/ex09/sample.csv");
		final FileReader fr2 = new FileReader("/Users/inoue-keiichi/git/Java-training/jpl/src/ch22/ex09/sample.csv");
		final FileReader fr3 = new FileReader("/Users/inoue-keiichi/git/Java-training/jpl/src/ch22/ex09/sample.csv");
		final FileReader fr4 = new FileReader("/Users/inoue-keiichi/git/Java-training/jpl/src/ch22/ex09/sample.csv");

		start = System.currentTimeMillis();
		Scan.readCSVTable(fr1, regex1);
		end = System.currentTimeMillis();
		System.out.println("#効率考えない");
		System.out.println(regex1 + ": " + (end - start) + "ms");

		start = System.currentTimeMillis();
		Scan.readCSVTable(fr2, regex2);
		end = System.currentTimeMillis();
		System.out.println("#カンマは一致しない");
		System.out.println(regex2 + ": " + (end - start) + "ms");

		start = System.currentTimeMillis();
		Scan.readCSVTable(fr3, regex3);
		end = System.currentTimeMillis();
		System.out.println("#最長一致の量指定子");
		System.out.println(regex3 + ": " + (end - start) + "ms");

		start = System.currentTimeMillis();
		Scan.readCSVTable(fr4, regex4);
		end = System.currentTimeMillis();
		System.out.println("#最短一致の量指定子");
		System.out.println(regex4 + ": " + (end - start) + "ms");
	}
}
