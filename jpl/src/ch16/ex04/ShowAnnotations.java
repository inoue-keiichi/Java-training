package ch16.ex04;

import java.lang.annotation.Annotation;
import java.util.Objects;

public class ShowAnnotations {
	public static void main(String[] args) {
		try {
			Class<?> c = Class.forName(args[0]);
			System.out.println(c);
			for (Annotation ann : c.getAnnotations()) {
				System.out.println(ann);
			}

		} catch (ClassNotFoundException e) {
			System.out.println("unknown class: " + args[0]);
		}
	}
}


@BugsFixed({ "12344", "34545" })
class Sample {

}
