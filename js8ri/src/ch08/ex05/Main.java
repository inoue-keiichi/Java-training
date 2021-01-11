package ch08.ex05;

import java.util.ArrayList;
import java.util.List;

public class Main {
	public static void main(final String[] args) {
		final List<String> words = new ArrayList<>() {
			{
				this.add("TanakaTaro");
				this.add("InoueKeiichi");
				this.add("SasakiKentaro");
				this.add("SatoHanako");
				this.add("TakayamaYumiko");
			}
		};

		words.removeIf(e -> e.length() <= 12);
		System.out.println(words.size());
	}
}
