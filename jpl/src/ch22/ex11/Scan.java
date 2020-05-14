package ch22.ex11;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StreamTokenizer;
import java.util.ArrayList;
import java.util.List;

public class Scan {
	public static List<String[]> readCSVTable(final Reader source, final int cellNum) throws IOException {
		List<String[]> list = new ArrayList<>();
		StreamTokenizer tokenizer = new StreamTokenizer(source);
		tokenizer.whitespaceChars(',', ',');

		String[] array = new String[cellNum];
		int token;
		int i = 0;
		int lineNum = 1;
		while ((token = tokenizer.nextToken()) != StreamTokenizer.TT_EOF) {
			// csvファイルの1行のcell数が引数より多いとエラー
			int num = tokenizer.lineno();
			if (lineNum != tokenizer.lineno()) {
				throw new IOException("too many kanma");
			}
			switch (token) {
			case StreamTokenizer.TT_EOL:
				i--;
				break;
			case StreamTokenizer.TT_NUMBER:
				array[i] = String.valueOf(tokenizer.nval);
				break;
			case StreamTokenizer.TT_WORD:
				array[i] = tokenizer.sval;
				break;
			default:
				break;
			}
			i++;
			if (i >= cellNum) {
				list.add(array);
				i = 0;
				array = new String[cellNum];
				lineNum++;
			}
		}
		return list;
	}

	public static void main(String[] args) throws IOException {
		FileReader fr = new FileReader("/Users/inoue-keiichi/git/Java-training/jpl/src/ch22/ex11/sample.csv");
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
