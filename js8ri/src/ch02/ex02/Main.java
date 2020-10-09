package ch02.ex02;

import java.util.ArrayList;
import java.util.List;

// 6文字以上の文字列で要素は5個までに制限
public class Main {
	public static void main(final String[] args) {
		final List<String> list = new ArrayList<String>() {
			{
				add("Japan");
				add("Australia");
				add("Canada");
				add("SouthAfrica");
				add("Thai");
				add("Brazil");
				add("England");
				add("France");
			}
		};

		list.stream().filter(e -> {
			if (e.length() < 6) {
				System.out.println("NO : " + e);
				return false;
			}
			System.out.println("OK : " + e);
			return true;
		}).limit(5).forEach(e -> {
		});
	}
}
