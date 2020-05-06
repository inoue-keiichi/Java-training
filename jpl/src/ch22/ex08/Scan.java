package ch22.ex08;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;

public class Scan {
	public static List<String[]> readCSVTable(final Readable source, final int cellNum) throws IOException {
		Scanner in = new Scanner(source);
		List<String[]> vals = new ArrayList<>();
		// 読み込む１行の正規表現を作成
		String exp = "^";
		for (int i = 0; i < cellNum - 1; i++) {
			exp += "(.*),";
		}
		exp += "(.*)";

		Pattern pat = Pattern.compile(exp, Pattern.MULTILINE);
		while (in.hasNextLine()) {
			String line = in.findInLine(pat);
			if (line != null) {
				String[] cells = new String[cellNum];
				MatchResult match = in.match();
				for (int i = 0; i < cellNum; i++) {
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
		FileReader fr = new FileReader("/Users/inoue-keiichi/git/Java-training/jpl/src/ch22/ex08/sample_blankLine.csv");
		List<String[]> list = Scan.readCSVTable(fr, 5);
		for (String[] strs : list) {
			for (String str : strs) {
				System.out.print(str);
				System.out.print(" ");
			}
			System.out.println();
		}
	}
}
