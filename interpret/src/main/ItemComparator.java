package main;

import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.util.Comparator;

public class ItemComparator implements Comparator<Member> {

	@Override
	public int compare(Member element1, Member element2) {
		String name1 = element1.getName();
		String name2 = element2.getName();
		return name1.compareTo(name2);
	}

}