package main;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public abstract class PrintGenerator {
	private List<Observer> observers = new ArrayList<>();

	public void addObserver(final Observer observer) {
		observers.add(observer);
	}

	public void notifyObservers() {
		for (Observer observer : observers) {
			observer.update(this);
		}
	}

	public abstract void execute() throws Throwable;
	
	public abstract String getLog();
}
