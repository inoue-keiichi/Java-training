package dc2_2.abstracts;

import java.util.ArrayList;
import java.util.List;

import dc2_2.di.DIService;
import dc2_2.interfaces.Observer;

public abstract class PrintGenerator {
	private List<Observer> observers = new ArrayList<>();
	private Object item;
	protected DIService service;

	public PrintGenerator(final DIService service) {
		this.service = service;
	}

	public void addObserver(final Observer observer) {
		observers.add(observer);
	}

	public void notifyObservers() {
		for (Observer observer : observers) {
			observer.update(this);
		}
	}

	public abstract void execute();
}
