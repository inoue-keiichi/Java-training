package ch22.ex04;

import java.util.Observable;
import java.util.Observer;

public class Eye<T> implements Observer {
	AttributedImpl attr;

	public Eye(AttributedImpl attr) {
		this.attr = attr;
		this.attr.addObserver(this);
	}

	@Override
	public void update(Observable attr, Object arg) {
		if (this.attr != attr) {
			throw new IllegalArgumentException();
		}

		System.out.println(arg + " is removed!");
	}

}
