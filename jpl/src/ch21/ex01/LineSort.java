package ch21.ex01;

import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import ch20.ex04.LineFilterReader;

public class LineSort {

	public static void main(String[] args) throws IOException {
		final String file = "/Users/inoue-keiichi/git/Java-training/jpl/src/ch21/ex01/sample.txt";
		System.out.println(createSortList(file));
	}

	public static List<String> createSortList(final String file) throws IOException {
		FileReader fileIn = new FileReader(file);
		LineFilterReader lfr = new LineFilterReader(fileIn);
		List<String> list = new LinkedList<>();
		String str;
		if ((str = lfr.readLine()) != null) {
			list.add(str);
		}
		while ((str = lfr.readLine()) != null) {
			for (int i = 0; i < list.size(); i++) {
				if (str.compareTo(list.get(i)) <= 0) {
					list.add(i, str);
					break;
				}
				if (i == list.size() - 1) {
					list.add(list.size(), str);
					break;
				}
			}
		}
		lfr.close();
		return list;
	}
}
