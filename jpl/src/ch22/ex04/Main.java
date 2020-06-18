package ch22.ex04;

import ch11.ex03.Attr;

public class Main {

	public static void main(String[] args) {
		final AttributedImpl attr = new AttributedImpl();
		final Eye eye = new Eye(attr);
		attr.add(new Attr<String>("aiueo"));
		attr.add(new Attr<String>("11111"));
		attr.remove("aiueo");
	}
}
