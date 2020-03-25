package main;

import java.util.ArrayList;
import java.util.List;

public abstract class PrintGenerator {
	private List<Observer> observers = new ArrayList<>();
	private Object item;

	public void addObserver(final Observer observer) {
		observers.add(observer);
	}

	public void notifyObservers() {
		for (Observer observer : observers) {
			observer.update(this);
		}
	}

	public void notifyObservers(final Object item) {
		this.item = item;
		for (Observer observer : observers) {
			observer.update(this);
		}
	}

	public Object getItem() {
		return item;
	}

	public abstract void execute() throws Throwable;

	public abstract String getLog();
}
